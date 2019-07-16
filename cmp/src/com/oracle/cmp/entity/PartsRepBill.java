package com.oracle.cmp.entity;

import java.util.Date;

public class PartsRepBill {
	private int billId;
	private String billflag;
	private Code code;
	private Parts parts;
	private int billcount;
	private Date billtime;
	private User user;
	private String billExplain;
	
	public String getBillExplain() {
		return billExplain;
	}
	public void setBillExplain(String billExplain) {
		this.billExplain = billExplain;
	}
	public String getBillflag() {
		return billflag;
	}
	public void setBillflag(String billflag) {
		this.billflag = billflag;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public int getBillId() {
		return billId;
	}
	public void setBillId(int billId) {
		this.billId = billId;
	}
	public Code getCode() {
		return code;
	}
	public void setCode(Code code) {
		this.code = code;
	}
	public Parts getParts() {
		return parts;
	}
	public void setParts(Parts parts) {
		this.parts = parts;
	}
	public int getBillcount() {
		return billcount;
	}
	public void setBillcount(int billcount) {
		this.billcount = billcount;
	}
	public Date getBilltime() {
		return billtime;
	}
	public void setBilltime(Date billtime) {
		this.billtime = billtime;
	}
	
}
