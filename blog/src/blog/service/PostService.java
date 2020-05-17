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
			System.out.println("Post getselectPostOne() try �Ϸ�");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Post getselectPostOne() catch ����");
		} finally {
			try {
				conn.close();
			} catch  (Exception e){
				e.printStackTrace();
			}
		}
		return post;
	}
	
	//�۾��� �Է� Dao �޾ƿ���
	public Post addPost(Post post) {
			postDao = new PostDao();
			Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			 postDao.insertPost(conn, post);
			 System.out.println("PostService Post addPost() try �Ϸ�");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("PostService Post addPost() catch ����");
		} finally {
			try {
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return post;
	}
	//���Ӻ��� ����¡
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
			int lastPage = count/rowPerPage; // Dao ȣ�� �� ó����
			if(count % rowPerPage != 0 || count/rowPerPage ==0) { 
				lastPage +=1;
			}
			map.put("subjectName", count);
			map.put("postBySubject", postBySubject);
			map.put("lastPage", lastPage);
			System.out.println("PostService try �Ϸ�");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("PostService catch ����");
		} finally {
			try {
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return map;
	}
	
	//map�� �̿��� �Ѱ����� ����¡�۾�
	public Map<String, Object> getPostList(int currentPage, int rowPerPage) {
		Map<String, Object> map = new HashMap<String, Object>();
		Connection conn = null;
		List<Post> list = null;
		try {
			conn = DBUtil.getConnection();
			postDao = new PostDao(); //�ٿ��� ������������ ���ο� ��ü����
			int beginRow = (currentPage-1)*rowPerPage; //Dao ȣ���� ó���� 
			list = postDao.selelctPostList(conn, beginRow, rowPerPage);
			int count = postDao.countPost(conn);
			int lastPage = count/rowPerPage; // Dao ȣ�� �� ó����
			if(count % rowPerPage != 0) { 
				lastPage +=1;
			}
			map.put("postlist", list);
			map.put("lastPage", lastPage);
			System.out.println("PostService Map<> getPostList() try �Ϸ�");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("PostService Map<> getPostList() catch ����");
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
