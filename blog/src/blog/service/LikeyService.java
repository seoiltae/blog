package blog.service;

import java.sql.Connection;

import blog.commons.DBUtil;
import blog.dao.LikeyDao;
import blog.vo.Likey;

public class LikeyService {
	private LikeyDao likeyDao;
	
	// �Ⱦ�� ���� 
	public void addLikeyno(Likey likey) {
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			likeyDao = new LikeyDao();
			Likey glikey = likeyDao.selectLikey(conn, likey); // ��� ���� �ִ��� Ȯ���Ѵ�
			if(glikey.getLikeyNo() == 0) { // glikey�� ���ϰ�� �μ�Ʈ�� ���� ��� �߰����ش�
					likeyDao.insertLikey(conn, likey);
				} else if(glikey.getLikeyCk()==0 && glikey.getLikeynoCk() ==1) { // likeyCk 0�̰� likeynoCk�� 1�ϋ� 
					likey.setLikeyCk(0);
					likey.setLikeynoCk(0);
					likeyDao.updateLikey(conn, likey); //00���� ������Ʈ ���ش�
				} else { //�� �ܿ��� 0 1 ���� ������Ʈ ���ش�
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
	
	// ���ƿ� ���� 
	public void addLikey(Likey likey) {
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			likeyDao = new LikeyDao();
			Likey glikey = likeyDao.selectLikey(conn, likey); // ��� ���� �ִ��� Ȯ���Ѵ�
			
			if(glikey.getLikeyNo() == 0) { // glikey�� ���ϰ�� �μ�Ʈ�� ���� ��� �߰����ش�
				likeyDao.insertLikey(conn, likey);
			} else if(glikey.getLikeyCk()==1 && glikey.getLikeynoCk() ==0) { // likeyCk 1�̰� likeynoCk�� 0�ϋ� 
				likey.setLikeyCk(0);
				likey.setLikeynoCk(0);
				likeyDao.updateLikey(conn, likey); //00���� ������Ʈ ���ش�
			} else { //�� �ܿ��� 1 0 ���� ������Ʈ ���ش�
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
	
	// ����Ʈ�ѹ����� �Ⱦ�� ī��Ʈ
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
	
	// ����Ʈ�ѹ����� ���ƿ� ī��Ʈ
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
