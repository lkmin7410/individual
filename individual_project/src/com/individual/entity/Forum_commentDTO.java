package com.individual.entity;

import java.util.Date;

public class Forum_commentDTO {

	private String seq;
	private String img_name;
	private String comment;
	private Date regdate;
	private String userid;
	
	public Forum_commentDTO(){
		
	}
	

	public Forum_commentDTO(String seq, String userid,String img_name, String comment) {
		this.seq = seq;
		this.img_name = img_name;
		this.comment = comment;
		this.userid = userid;
	}

	

	public String getSeq() {
		return seq;
	}


	public void setSeq(String seq) {
		this.seq = seq;
	}


	public String getImg_name() {
		return img_name;
	}
	public void setImg_name(String img_name) {
		this.img_name = img_name;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	
	
	
}
