package blog.service;

import java.sql.Connection;

import blog.commons.DBUtil;
import blog.dao.LikeyDao;
import blog.vo.Likey;

public class LikeyService {
	private LikeyDao likeyDao;
	
	// 싫어요 서비스 
	public void addLikeyno(Likey likey) {
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			likeyDao = new LikeyDao();
			Likey glikey = likeyDao.selectLikey(conn, likey); // 디비에 값이 있는지 확인한다
			if(glikey.getLikeyNo() == 0) { // glikey가 널일경우 인설트로 값을 디비에 추가해준다
					likeyDao.insertLikey(conn, likey);
				} else if(glikey.getLikeyCk()==0 && glikey.getLikeynoCk() ==1) { // likeyCk 0이고 likeynoCk가 1일떄 
					likey.setLikeyCk(0);
					likey.setLikeynoCk(0);
					likeyDao.updateLikey(conn, likey); //00으로 업데이트 해준다
				} else { //그 외에는 0 1 으로 업데이트 해준다
					likey.setLikeyCk(0);
					likey.setLikeynoCk(1);
					likeyDao.updateLikey(conn, likey);
				}
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
	
	// 좋아요 서비스 
	public void addLikey(Likey likey) {
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			likeyDao = new LikeyDao();
			Likey glikey = likeyDao.selectLikey(conn, likey); // 디비에 값이 있는지 확인한다
			
			if(glikey.getLikeyNo() == 0) { // glikey가 널일경우 인설트로 값을 디비에 추가해준다
				likeyDao.insertLikey(conn, likey);
			} else if(glikey.getLikeyCk()==1 && glikey.getLikeynoCk() ==0) { // likeyCk 1이고 likeynoCk가 0일떄 
				likey.setLikeyCk(0);
				likey.setLikeynoCk(0);
				likeyDao.updateLikey(conn, likey); //00으로 업데이트 해준다
			} else { //그 외에는 1 0 으로 업데이트 해준다
				likey.setLikeyCk(1);
				likey.setLikeynoCk(0);
				likeyDao.updateLikey(conn, likey);
			}
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
	
	// 포스트넘버마다 싫어요 카운트
	public int getLikeynoCount(int postNo) {
		Connection conn = null;
		int count = 0;
		try {
			conn = DBUtil.getConnection();
			likeyDao = new LikeyDao();		
			count = likeyDao.selectLikeynoCount(conn, postNo);
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return count;
	}
	
	// 포스트넘버마다 좋아요 카운트
	public int getLikeyCount(int postNo) {
		Connection conn = null;
		int count = 0;
		try {
			conn = DBUtil.getConnection();
			likeyDao = new LikeyDao();		
			count = likeyDao.selectLikeyCount(conn, postNo);
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return count;
	}
	

}
