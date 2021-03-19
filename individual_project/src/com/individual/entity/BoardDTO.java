package com.individual.entity;

import java.util.Date;

public class BoardDTO {

	private int wid;
	private String img_name;
	private String comment;
	private int score;
	private Date regdate;
	private String userid;
	private String delflag;
	
	
	public BoardDTO() {
	}

	public int getWid() {
		return wid;
	}
	public void setWid(int wid) {
		this.wid = wid;
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
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
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
	public String getDelflag() {
		return delflag;
	}
	public void setDelflag(String delflag) {
		this.delflag = delflag;
	}
	
	
}
