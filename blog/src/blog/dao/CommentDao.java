package blog.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import blog.vo.Comment;

public class CommentDao {
	
	//총갯수 구하기 count(*) postNo마다 총갯수
	public int countCommentOne(Connection conn, int postNo) throws SQLException{
		int count = 0;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT COUNT(*) FROM comment WHERE post_no=?";
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, postNo);
			rs = stmt.executeQuery();
			if(rs.next()) {
				count = rs.getInt("COUNT(*)"); // 구해야할 값이 하나면 안에 COUNT(*)대신 숫자 1을 넣어도 된다
			}
		} finally {
			rs.close();
			stmt.close();
		}
		return count;
	}
	
	//postNO 별로 댓글 보이고 페이징
	public List<Comment> selectPostbyCommentList(Connection conn, int postNo, int beginRow, int rowPerPage) throws SQLException{
		List<Comment> list = new ArrayList<Comment>();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM comment WHERE post_no=? LIMIT ?, ?";
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, postNo);
			stmt.setInt(2, beginRow);
			stmt.setInt(3, rowPerPage);
			rs = stmt.executeQuery();
			while (rs.next()) {
				Comment comment = new Comment();
				comment.setCommentNo(rs.getInt("comment_no"));
				comment.setPostNo(rs.getInt("post_no"));
				comment.setMemberId(rs.getString("member_id"));
				comment.setCommentContent(rs.getString("comment_content"));
				comment.setCommentDate(rs.getString("comment_date"));
				list.add(comment);
			}
		} finally {
			rs.close();
			stmt.close();
		}
		return list;
	}
	
	public void insertComment(Connection conn, Comment comment) throws SQLException {
		PreparedStatement stmt = null;
		String sql = "INSERT INTO comment(post_no, member_id, comment_content, comment_date) VALUES(?, ?, ?, NOW())";
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, comment.getPostNo());
			stmt.setString(2, comment.getMemberId());
			stmt.setString(3, comment.getCommentContent());
			stmt.executeUpdate();
			System.out.println("CommentDao insertComment()");
		} finally {
			stmt.close();
		}
	}
}
