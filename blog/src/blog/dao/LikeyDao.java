package blog.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import blog.vo.Likey;

public class LikeyDao {
	
	// 좋아요 확인	
	public Likey selectLikey(Connection conn, Likey likey) throws SQLException {
		String sql = "SELECT * FROM likey WHERE post_no=? AND member_id=?";
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Likey likeye = new Likey();
	try {
		stmt = conn.prepareStatement(sql);
		stmt.setInt(1, likey.getPostNo());
		stmt.setString(2, likey.getMemberId());
		rs = stmt.executeQuery();
		if(rs.next()) {
			likeye.setLikeyNo(rs.getInt("likey_no"));
			likeye.setPostNo(rs.getInt("post_no"));
			likeye.setMemberId(rs.getString("member_id"));
			likeye.setLikeyCk(rs.getInt("likey_ck"));
			likeye.setLikeynoCk(rs.getInt("likeyno_ck"));
			likeye.setLikeyDate(rs.getString("likey_date"));
		}
		System.out.println(likeye);
	} finally {
		rs.close();
		stmt.close();
	}
	return likeye;
	}
	
	
	//싫어요 Nolikey값
	public int isLikeyno(Connection conn, Likey likey) throws SQLException {
			String sql = "SELECT likeyno_ck FROM likey WHERE post_no=? AND member_id=?";
			PreparedStatement stmt = null;
			ResultSet rs = null;
			int likeynoCk = 2;
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, likey.getPostNo());
			stmt.setString(2, likey.getMemberId());
			rs = stmt.executeQuery();
			if(rs.next()) {
				likeynoCk = rs.getInt("likeyno_ck");
			}
		} finally {
			rs.close();
			stmt.close();
		}
		return likeynoCk;
	}
	
	//좋아요 likey값
	public int isLikey(Connection conn, Likey likey) throws SQLException {
			String sql = "SELECT likey_ck FROM likey WHERE post_no=? AND member_id=?";
			PreparedStatement stmt = null;
			ResultSet rs = null;
			int likeyCk = 2;
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, likey.getPostNo());
			stmt.setString(2, likey.getMemberId());
			rs = stmt.executeQuery();
			if(rs.next()) {
				likeyCk = rs.getInt("likey_ck");
			}
		} finally {
			rs.close();
			stmt.close();
		}
		return likeyCk;
	}
	
	//좋아요 likey값
	public boolean isLikeytrue(Connection conn, Likey likey) throws SQLException {
			boolean flag = true;
			String sql = "SELECT likey_ck FROM likey WHERE post_no=? AND member_id=?";
			PreparedStatement stmt = null;
			ResultSet rs = null;
			int likeyCk = 2;
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, likey.getPostNo());
			stmt.setString(2, likey.getMemberId());
			rs = stmt.executeQuery();
			if(rs.next()) {
				flag = false;
			}
		} finally {
			rs.close();
			stmt.close();
		}
		return flag;
	}
	
	
		// 싫어요 수정
		public void updateLikeyno(Connection conn, Likey likey) throws SQLException {
				String sql ="UPDATE likey set likey_ck=0 AND likeyno_ck=1 WEHRE post_no=?, member_id=?";
				PreparedStatement stmt = null;
			try {
				stmt = conn.prepareStatement(sql);
				stmt.setInt(1, likey.getPostNo());
				stmt.setString(2, likey.getMemberId());
				stmt.executeUpdate();
			} finally {
				stmt.close();
			}
		}
	
		// 좋아요 수정
		public void updateLikey(Connection conn, Likey likey) throws SQLException {
				String sql ="UPDATE likey set likey_ck=?, likeyno_ck=? WHERE post_no=? AND member_id=?";
				PreparedStatement stmt = null;
			try {
				stmt = conn.prepareStatement(sql);
				stmt.setInt(1, likey.getLikeyCk());
				stmt.setInt(2, likey.getLikeynoCk());
				stmt.setInt(3, likey.getPostNo());
				stmt.setString(4, likey.getMemberId());
				stmt.executeUpdate();
			} finally {
				stmt.close();
			}
		}
		
		
	// 좋아요 삭제 AND 싫어요 삭제
	public void DeleteLikey(Connection conn, Likey likey) throws SQLException {
		
			//"UPDATE likey set likey_ck=0 WHERE post_no=?";
			String sql ="Delete FROM likey WHERE post_no=? AND member_id=?";
			PreparedStatement stmt = null;
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, likey.getPostNo());
			stmt.setString(2, likey.getMemberId());
			stmt.executeQuery();
		} finally {
			stmt.close();
		}
	}
	
	
	// 싫어요 누르기
		public void insertLikeyno(Connection conn, Likey likey) throws SQLException {
				String sql ="INSERT INTO likey(post_no, member_id, likeyno_ck, likey_ck, likey_date) VALUES(?, ?, 1, 0, NOW())";
				PreparedStatement stmt = null;
			try {
				stmt = conn.prepareStatement(sql);
				stmt.setInt(1, likey.getPostNo());
				stmt.setString(2, likey.getMemberId());
				stmt.executeUpdate();
			} finally {
				stmt.close();
			}
		}
		
	
	// 좋아요 누르기
	public void insertLikey(Connection conn, Likey likey) throws SQLException {
			String sql ="INSERT INTO likey(post_no, member_id, likeyno_ck, likey_ck, likey_date) VALUES(?, ?, 0, 1, NOW())";
			PreparedStatement stmt = null;
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, likey.getPostNo());
			stmt.setString(2, likey.getMemberId());
			stmt.executeUpdate();
		} finally {
			stmt.close();
		}
	}
	
	//Likeyno_ck 계산
	public int selectLikeynoCount(Connection conn, int postNo) throws SQLException {
			int count = 0;
			String sql ="SELECT SUM(likeyno_ck) FROM likey WHERE likeyno_ck=1 AND post_no=?";
			PreparedStatement stmt = null;
			ResultSet rs = null;
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, postNo);
			rs = stmt.executeQuery();
			if(rs.next()) {
				count = rs.getInt(1); // rs.getInt("SUM(likey_ck)")
			}
		} finally {
			stmt.close();
		}
		return count;
	}
	
	//likey_ck 계산
	public int selectLikeyCount(Connection conn, int postNo) throws SQLException {
			int count = 0;
			String sql ="SELECT SUM(likey_ck) FROM likey WHERE likey_ck=1 AND post_no=?";
			PreparedStatement stmt = null;
			ResultSet rs = null;
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, postNo);
			rs = stmt.executeQuery();
			if(rs.next()) {
				count = rs.getInt(1); // rs.getInt("SUM(likey_ck)")
			}
		} finally {
			stmt.close();
		}
		return count;
	}
}
