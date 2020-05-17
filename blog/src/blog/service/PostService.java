package blog.service;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import blog.commons.DBUtil;
import blog.dao.PostDao;
import blog.vo.Post;
import blog.vo.Subject;

public class PostService {
	private PostDao postDao;
	
	public Post getselectPostOne(int postNo) {
			postDao = new PostDao();
			Post post = new Post();
			Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			post = postDao.selectPostOne(conn, postNo);
			System.out.println("Post getselectPostOne() try 완료");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Post getselectPostOne() catch 에러");
		} finally {
			try {
				conn.close();
			} catch  (Exception e){
				e.printStackTrace();
			}
		}
		return post;
	}
	
	//글쓰기 입력 Dao 받아오기
	public Post addPost(Post post) {
			postDao = new PostDao();
			Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			 postDao.insertPost(conn, post);
			 System.out.println("PostService Post addPost() try 완료");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("PostService Post addPost() catch 에러");
		} finally {
			try {
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return post;
	}
	//네임별로 페이징
	public Map<String, Object> getselectPostBySubject(String subjectName, int currentPage, int rowPerPage) {
		Connection conn = null;
		List<Post> postBySubject = null;
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			conn = DBUtil.getConnection();
			postDao = new PostDao();
			int beginRow = (currentPage-1)*rowPerPage;
			postBySubject = postDao.selectPostBySubject(conn, subjectName, beginRow, rowPerPage);
			int count = postDao.countPostOne(conn, subjectName);
			int lastPage = count/rowPerPage; // Dao 호출 후 처리값
			if(count % rowPerPage != 0 || count/rowPerPage ==0) { 
				lastPage +=1;
			}
			map.put("subjectName", count);
			map.put("postBySubject", postBySubject);
			map.put("lastPage", lastPage);
			System.out.println("PostService try 완료");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("PostService catch 에러");
		} finally {
			try {
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return map;
	}
	
	//map을 이용해 총갯수랑 페이징작업
	public Map<String, Object> getPostList(int currentPage, int rowPerPage) {
		Map<String, Object> map = new HashMap<String, Object>();
		Connection conn = null;
		List<Post> list = null;
		try {
			conn = DBUtil.getConnection();
			postDao = new PostDao(); //다오를 가져오기위해 새로운 객체생성
			int beginRow = (currentPage-1)*rowPerPage; //Dao 호출전 처리값 
			list = postDao.selelctPostList(conn, beginRow, rowPerPage);
			int count = postDao.countPost(conn);
			int lastPage = count/rowPerPage; // Dao 호출 후 처리값
			if(count % rowPerPage != 0) { 
				lastPage +=1;
			}
			map.put("postlist", list);
			map.put("lastPage", lastPage);
			System.out.println("PostService Map<> getPostList() try 완료");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("PostService Map<> getPostList() catch 에러");
		} finally {
			try {
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return map; 
	}
}
