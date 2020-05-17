package blog.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import blog.vo.Post;

public class PostDao {
	
	//상세보기 하기 int postNo
	public Post selectPostOne(Connection conn, int postNo) throws SQLException{ 
		Post post = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM post WHERE post_no=?";
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, postNo);
			rs = stmt.executeQuery();
			if(rs.next()) {
				post = new Post();
				post.setSubjectName(rs.getString("subject_name"));
				post.setPostTitle(rs.getString("post_title"));
				post.setMemberId(rs.getString("member_id"));
				post.setSubjectName(rs.getString("subject_name"));
				post.setPostDate(rs.getString("post_date"));
				post.setPostContent(rs.getString("post_content"));
			}
		} finally {
			rs.close();
			stmt.close();
		}
		return post;
	}
	
	//총갯수 구하기 count(*) 서브젝트마다 총갯수
	public int countPostOne(Connection conn, String subjectName) throws SQLException{
		int count = 0;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT COUNT(*) FROM post WHERE subject_name=?";
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, subjectName);
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
	
	//글쓰기 추가 INSERT
	public void insertPost(Connection conn, Post post) throws SQLException{
		PreparedStatement stmt = null;
		String sql = "INSERT INTO post(member_id, subject_name, post_title, post_content, post_date) VALUES"
				+ "(?, ?, ?, ?, NOW())";
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, post.getMemberId());
			stmt.setString(2, post.getSubjectName());
			stmt.setString(3, post.getPostTitle());
			stmt.setString(4, post.getPostContent());
			stmt.executeUpdate();
			System.out.println("PostDao insertPost() try 완료");
		} finally {
			stmt.close();
		}
	}
	
	//서브젝트 별로 페이징
	public List<Post> selectPostBySubject(Connection conn, String subjectName, int beginRow, int rowPerPage) throws SQLException{
		PreparedStatement stmt = null;
		List<Post> list = new ArrayList<Post>();
		ResultSet rs = null;
		String sql = "SELECT * FROM post WHERE subject_name=? LIMIT ?, ?";
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, subjectName);
			stmt.setInt(2, beginRow);
			stmt.setInt(3, rowPerPage);
			rs = stmt.executeQuery();
			while(rs.next()) {
				Post post = new Post();
				post.setPostNo(rs.getInt("post_no"));
				post.setPostTitle(rs.getString("post_title"));
				post.setMemberId(rs.getString("member_id"));
				post.setSubjectName(rs.getString("subject_name"));
				post.setPostDate(rs.getString("post_date"));
				post.setPostContent(rs.getString("post_content"));
				list.add(post);
			}
		} finally {
			rs.close();
			stmt.close();
		}
		return list;
	}

	//총갯수 구하기 count(*)
	public int countPost(Connection conn) throws SQLException{
		int count = 0;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT COUNT(*) FROM post";
		try {
			stmt = conn.prepareStatement(sql);
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
	
	//페이징 작업 및 postList불러오기
	public List<Post> selelctPostList(Connection conn, int beginRow, int rowPerPage)  throws SQLException{
		List<Post> list = new ArrayList<Post>();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT post_no, member_id, subject_name, post_title, post_date FROM post Limit ?, ?";
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, beginRow);
			stmt.setInt(2, rowPerPage);
			rs = stmt.executeQuery();
			while(rs.next()) {
				Post post = new Post();
				post.setPostNo(rs.getInt("post_no"));
				post.setMemberId(rs.getString("member_id"));
				post.setSubjectName(rs.getString("subject_name"));
				post.setPostTitle(rs.getString("post_title"));
				post.setPostDate(rs.getString("post_date"));
				list.add(post);
			}
			System.out.println("PostDao List<Post> selelctPostListAll() try ");
		} finally {
			rs.close();
			stmt.close();
		}
		return list;
	}
}
