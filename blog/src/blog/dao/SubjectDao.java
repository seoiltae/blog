package blog.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import blog.vo.Subject;


public class SubjectDao {
	
	public List<Subject> selectSubjectListAll(Connection conn) throws SQLException{
		List<Subject> list = new ArrayList<Subject>(); //size -> 0
		String sql = "SELECT subject_name FROM subject ORDER By subject_name ASC";
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
		while(rs.next()) { // name에 있는 데이터를 전부 불러와야되니 반복문!
			Subject subject = new Subject();
			subject.setSubjectName(rs.getString("subject_name"));
			list.add(subject);
		}
		}  finally {
			stmt.close();
		}
		return list;
	}
}
