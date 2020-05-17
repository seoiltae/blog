package blog.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MemberidDao {
	// ȸ�� Ż�� �� Ż����̵� �߰��Ѵ� 
	public void insertMemberid(Connection conn, String memberId) throws SQLException{
		PreparedStatement stmt = null;
		String sql ="INSERT INTO memberid(memberid, memberid_date) VALUES(?, NOW())";
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, memberId);
			stmt.executeUpdate();
		} finally  {
			System.out.println("MemberidDao insertMemberid(Connection conn, String memberId) finally");
			stmt.close();
		}
	}
}
