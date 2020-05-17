package blog.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import blog.commons.DBUtil;
import blog.vo.Member;

public class MemberDao {
	
	//�Ѱ��� ���ϱ�
	public int countMember(Connection conn) throws SQLException{
		int count = 0;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT COUNT(*) FROM member";
		try {
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			if(rs.next()) {
				count = rs.getInt(1); //
			}
		} finally {
			rs.close();
			stmt.close();
		}
		return count;
	}
	
	//ȸ������ �� ����¡ �۾�
	public List<Member> selectMemberList(Connection conn,  int beginRow, int rowPerPage) throws SQLException{
		PreparedStatement stmt = null;
		List<Member> list = new ArrayList<Member>();
		ResultSet rs = null;
		String sql = "SELECT * FROM member Limit ?, ?";
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, beginRow);
			stmt.setInt(2, rowPerPage);
			rs = stmt.executeQuery();
			while(rs.next()) {
				Member member = new Member();
				member.setMemberId(rs.getString("member_id"));
				member.setMemberLevel(rs.getInt("member_level"));;
				member.setMemberPw(rs.getString("member_pw"));
				member.setMemberDate(rs.getString("member_date"));
				list.add(member);
			}
		} finally {
				rs.close();
				stmt.close();
		}
		return list;
	}
	
	//ȸ������!
	public void insertMember(Connection conn, Member member) throws SQLException{
		PreparedStatement stmt = null;
		String sql = "INSERT INTO member(member_id, member_pw, member_level, member_date) VALUES(?, ?, 10, NOW())";
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, member.getMemberId());
			stmt.setString(2, member.getMemberPw());
			stmt.executeUpdate();
			System.out.println("MemberDao insertMember(Connection conn, Member member)");
		} finally {
			stmt.close();
		}
	}
	
	//ȸ������ ���� id ��� ����
	// ���̵� ��밡���ϸ� true-���� ����
	// ���̵� ��� �Ұ��� false-���� �ִ�
	public boolean selectMemberId (Connection conn, String memberId) throws SQLException{
		boolean flag = true;
		PreparedStatement stmt = null;
		// UNION, SUBQUERY, JOIN
		String sql = "SELECT mi FROM "
				+ "(SELECT member_id mi FROM member UNION SELECT memberid mi FROM memberid) t "
				+ "WHERE t.mi=?";
		ResultSet rs = null;
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1,  memberId);
			rs = stmt.executeQuery();
			if(rs.next()) {
				flag = false;
			}
			System.out.println("MemberDao boolean selectMemberId (Connection conn, String memberId)");
		} finally {
			stmt.close();
			rs.close();
		}
		
		return flag;		
	}
	
	//ȸ�� Ż��
	public int delecteMember(Connection conn, Member member) throws SQLException {
		PreparedStatement stmt = null;
		String sql ="DELETE FROM member WHERE member_id=? AND member_pw=?";
		int row = 0;
		try {
			stmt = conn.prepareStatement(sql);
			System.out.println("MemberDao delectMember(Connection conn, Member member) try");
			stmt.setString(1, member.getMemberId());
			stmt.setString(2, member.getMemberPw());
			row = stmt.executeUpdate();
		} finally {
			System.out.println("MemberDao delectMember(Connection conn, Member member) finally");
			stmt.close();
		}
		return row; //������ ���� �� 
	}
	
	public Member selectMemberOne(Connection conn, String memberId) throws SQLException{
		Member member = null;
		String sql = "SELECT * FROM member WHERE member_id=?";
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, memberId);
			rs = stmt.executeQuery();
		if(rs.next()) {
			member = new Member();
			member.setMemberId(rs.getString("member_id"));
			member.setMemberLevel(rs.getInt("member_level"));;
			System.out.println("MemberDao  selectMemberOne(Connection conn, String memberId) try");
			}
		}  finally {
			System.out.println("MemberDao  selectMemberOne(Connection conn, String memberId) finally");
			stmt.close();
			rs.close();
			}
			return member;	
		}
	
	//�α��� �Ҷ� �����ͺ��̽��� ���̵�� ������ �Է��� ���̵� ��ġ�ϴ��� Ȯ���ϱ� ����
	public Member selectMemberOne(Member member) { //���̵� �� �н�����
			Member returnMember = null;
			String sql = "SELECT * FROM member WHERE member_id=? AND member_pw=?";
			Connection conn = null;
			PreparedStatement stmt = null;
			ResultSet rs = null;
			try {
				conn = DBUtil.getConnection();
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, member.getMemberId());
				stmt.setString(2, member.getMemberPw());
				rs = stmt.executeQuery();
			if(rs.next()) { 
				returnMember = new Member();
				returnMember.setMemberId(rs.getString("member_id"));
				returnMember.setMemberLevel(rs.getInt("member_level"));;
				returnMember.setMemberDate(rs.getString("member_date"));
				returnMember.setMemberNo(rs.getInt("member_no"));
			}
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("Member selectMemberOne() catch ����");
			} finally {
				DBUtil.close(rs, stmt, conn);
			}
			return returnMember;
	}
}
	
