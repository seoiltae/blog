package blog.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import blog.commons.DBUtil;
import blog.dao.MemberDao;
import blog.dao.MemberidDao;
import blog.vo.Member;

public class MemberService {
	private MemberDao memberDao;
	private MemberidDao memberidDao;
	
	//map을 이용해서 map안에 memberlist와 lastPage 넘겨주기
	public Map<String, Object> getMemberList(int currentPage, int rowPerPage) {
		Map<String, Object> map = new HashMap<String, Object>();
		Connection conn = null;
		List<Member> list = null;
		try {
			conn = DBUtil.getConnection();
			memberDao = new MemberDao();
			int beginRow = (currentPage-1)*rowPerPage; // before Dao 호출
			list = memberDao.selectMemberList(conn, beginRow, rowPerPage);
			int count = memberDao.countMember(conn);
			int lastPage = count / rowPerPage; // after dao 호출
			if(count % rowPerPage != 0) {
				lastPage +=1;
			}
			
			map.put("list", list);
			map.put("lastPage", lastPage);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return map;
	}
	
	// boolean 
	public boolean addMember(Member member) {
		memberDao = new MemberDao();
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			if(memberDao.selectMemberId(conn, member.getMemberId())) {
				memberDao.insertMember(conn, member);
				return true;	
			} 
			System.out.println(" MemberService boolean addMember(Member member) try 완료");
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println(" MemberService boolean addMember(Member member) catch 에러");
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}
	
	
	public Member removerMember(String memberId) {
		Connection conn = null;
		Member member = null;
		try {
			conn = DBUtil.getConnection();
			memberDao = new MemberDao();
			member = memberDao.selectMemberOne(conn, memberId);
			System.out.println("MemberService removerMember(String memberId) try 완료확인");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("MemberService removerMember(String memberId) catch 에러");
		} finally {
			DBUtil.close(null, null, conn);
		}
		return member;
	}
	
	// 회원 탈퇴와 동시에 중복방지
	public void removeMember(Member member) {
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			conn.setAutoCommit(false); //
			System.out.println("MemberService removeMember(Member member)");
			memberDao = new MemberDao();
			int row	= memberDao.delecteMember(conn, member); 
			if(row == 1) { //비밀번호가 같아서 삭제 후
				memberidDao = new MemberidDao(); //new 연산자를 이용해서 객체를 생성!
				memberidDao.insertMemberid(conn, member.getMemberId());
			}
			conn.commit(); //위에서 실행한 것을 conn에 저장한다
		} catch (Exception e) {
			try {
				conn.rollback(); // 예외로 넘어올 경우 conn에 저장한 것을 롤백(저장하기전으로 되돌린다)
			} catch (SQLException e1) {
				e1.printStackTrace();
				System.out.println("MemberService removeMember(Member member)catch");
			}
			e.printStackTrace();
		} finally {
			DBUtil.close(null, null, conn);
		}
	
	}
}
