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
	
	//map�� �̿��ؼ� map�ȿ� memberlist�� lastPage �Ѱ��ֱ�
	public Map<String, Object> getMemberList(int currentPage, int rowPerPage) {
		Map<String, Object> map = new HashMap<String, Object>();
		Connection conn = null;
		List<Member> list = null;
		try {
			conn = DBUtil.getConnection();
			memberDao = new MemberDao();
			int beginRow = (currentPage-1)*rowPerPage; // before Dao ȣ��
			list = memberDao.selectMemberList(conn, beginRow, rowPerPage);
			int count = memberDao.countMember(conn);
			int lastPage = count / rowPerPage; // after dao ȣ��
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
			System.out.println(" MemberService boolean addMember(Member member) try �Ϸ�");
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println(" MemberService boolean addMember(Member member) catch ����");
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
			System.out.println("MemberService removerMember(String memberId) try �Ϸ�Ȯ��");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("MemberService removerMember(String memberId) catch ����");
		} finally {
			DBUtil.close(null, null, conn);
		}
		return member;
	}
	
	// ȸ�� Ż��� ���ÿ� �ߺ�����
	public void removeMember(Member member) {
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			conn.setAutoCommit(false); //
			System.out.println("MemberService removeMember(Member member)");
			memberDao = new MemberDao();
			int row	= memberDao.delecteMember(conn, member); 
			if(row == 1) { //��й�ȣ�� ���Ƽ� ���� ��
				memberidDao = new MemberidDao(); //new �����ڸ� �̿��ؼ� ��ü�� ����!
				memberidDao.insertMemberid(conn, member.getMemberId());
			}
			conn.commit(); //������ ������ ���� conn�� �����Ѵ�
		} catch (Exception e) {
			try {
				conn.rollback(); // ���ܷ� �Ѿ�� ��� conn�� ������ ���� �ѹ�(�����ϱ������� �ǵ�����)
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
