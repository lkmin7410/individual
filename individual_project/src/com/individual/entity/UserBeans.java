package com.individual.entity;

public class UserBeans {

	private String id;
	private String pw;
	private String name;
	private String email;
	private String flag;
	private String naver_name;
	private String naver_email;
	private String naver_nickname;
	
	public UserBeans(String id, String pw, String name, String email) {
		super();
		this.id = id;
		this.pw = pw;
		this.name = name;
		this.email = email;
		
	}

	
	public UserBeans() {
	}


	public UserBeans(String id, String pw) {
		super();
		this.id = id;
		this.pw = pw;
	}

	
	public String getNaver_name() {
		return naver_name;
	}


	public void setNaver_name(String naver_name) {
		this.naver_name = naver_name;
	}


	public String getNaver_email() {
		return naver_email;
	}


	public void setNaver_email(String naver_email) {
		this.naver_email = naver_email;
	}


	public String getNaver_nickname() {
		return naver_nickname;
	}


	public void setNaver_nickname(String naver_nickname) {
		this.naver_nickname = naver_nickname;
	}


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}
	
	
	
}
