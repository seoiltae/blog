package blog.service;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import blog.commons.DBUtil;
import blog.dao.CommentDao;
import blog.vo.Comment;


public class CommentService {
	private CommentDao commentDao;
	
	//map으로 리스트와 페이징, 총갯수 다오 호출
	public Map<String, Object> getselectPostbyCommentList(int postNo, int currentPage, int rowPerPage) {
			Connection conn = null;
			List<Comment> selectPostbyCommentList = null; 
			Map<String, Object> map = new HashMap<String, Object>();
		try {
			conn = DBUtil.getConnection();
			commentDao = new CommentDao();
			int beginRow = (currentPage-1)*rowPerPage;
			selectPostbyCommentList = commentDao.selectPostbyCommentList(conn, postNo, beginRow, rowPerPage);
			int count = commentDao.countCommentOne(conn, postNo);
			int lastPage = count/rowPerPage;
			if(count % rowPerPage != 0 || count/rowPerPage ==0) {
				lastPage +=1;
			} 
			
			map.put("postNo", postNo);
			map.put("postbyCommentList", selectPostbyCommentList);
			map.put("lastPage", lastPage);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("CommentService Map<> getselectPostbyCommentList() catch 에러");
		} finally {
			try {
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return map;
	}
	
	//댓글 입력 보이드타입
	public void addComment(Comment comment) {
			commentDao = new CommentDao();
			Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			commentDao.insertComment(conn, comment);
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (Exception e){
				e.printStackTrace();
			}
		}
	}
}
