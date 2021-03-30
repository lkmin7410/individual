package com.individual.entity;

import java.util.Date;

public class ContentDTO {

	private int seq;
	private String title;
	private String content;
	private Date regdate;
	private String writer;
	private int hit;
	private String img;
	private String delflag;
	
	public ContentDTO(String title, String content, Date regdate, String writer, int hit) {
		this.title = title;
		this.content = content;
		this.regdate = regdate;
		this.writer = writer;
		this.hit = hit;
	}
	public ContentDTO(String title, String content, String writer, String img) {
		this.title = title;
		this.content = content;
		this.writer = writer;
		this.img = img;
	}
	
	public ContentDTO(String title, String content, String writer, String img, int seq) {
		this.title = title;
		this.content = content;
		this.writer = writer;
		this.img = img;
		this.seq = seq;
	}
	public ContentDTO(String title, String content, String writer, String img, int seq,String delflag) {
		this.title = title;
		this.content = content;
		this.writer = writer;
		this.img = img;
		this.seq = seq;
	}
	
	public ContentDTO() {}


	public String getDelflag() {
		return delflag;
	}
	public void setDelflag(String delflag) {
		this.delflag = delflag;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public int getHit() {
		return hit;
	}
	public void setHit(int hit) {
		this.hit = hit;
	}
	
	
	
	
	
}
