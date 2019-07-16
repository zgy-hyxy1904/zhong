package com.oracle.cmp.entity;

import java.util.Date;

public class User {
	private int userId;
	private String loginName;
	private String loginPwd;
	private Date loginTime;
	private Emp e;
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getLoginPwd() {
		return loginPwd;
	}
	public void setLoginPwd(String loginPwd) {
		this.loginPwd = loginPwd;
	}
	public Date getLoginTime() {
		return loginTime;
	}
	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}
	public Emp getE() {
		return e;
	}
	public void setE(Emp e) {
		this.e = e;
	}
	
}
