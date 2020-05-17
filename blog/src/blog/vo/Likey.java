package blog.vo;

public class Likey {
	private int likeyNo;
	public int getLikeyNo() {
		return likeyNo;
	}
	public void setLikeyNo(int likeyNo) {
		this.likeyNo = likeyNo;
	}
	public int getPostNo() {
		return postNo;
	}
	public void setPostNo(int postNo) {
		this.postNo = postNo;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public int getLikeyCk() {
		return likeyCk;
	}
	public void setLikeyCk(int likeyCk) {
		this.likeyCk = likeyCk;
	}
	public String getLikeyDate() {
		return likeyDate;
	}
	public void setLikeyDate(String likeyDate) {
		this.likeyDate = likeyDate;
	}
	
	public int getLikeynoCk() {
		return likeynoCk;
	}
	public void setLikeynoCk(int likeynoCk) {
		this.likeynoCk = likeynoCk;
	}

	private int postNo;
	private String memberId;
	private int likeyCk;
	private String likeyDate;
	private int likeynoCk; 
}
