package com.oracle.cmp.entity;

import java.util.Date;

public class Parts {
	private int partsId;
	private String partsName;
	private String partsModel;
	private String partsLoc;
	private Date partsProDate;
	private String partsRemark;
	private PartsRepertory partsRepertory;
	public PartsRepertory getPartsRepertory() {
		return partsRepertory;
	}
	public void setPartsRepertory(PartsRepertory partsRepertory) {
		this.partsRepertory = partsRepertory;
	}
	public String getPartsModel() {
		return partsModel;
	}
	public void setPartsModel(String partsModel) {
		this.partsModel = partsModel;
	}
	public int getPartsId() {
		return partsId;
	}
	public void setPartsId(int partsId) {
		this.partsId = partsId;
	}
	public String getPartsName() {
		return partsName;
	}
	public void setPartsName(String partsName) {
		this.partsName = partsName;
	}
	public String getPartsLoc() {
		return partsLoc;
	}
	public void setPartsLoc(String partsLoc) {
		this.partsLoc = partsLoc;
	}
	public Date getPartsProDate() {
		return partsProDate;
	}
	public void setPartsProDate(Date partsProDate) {
		this.partsProDate = partsProDate;
	}
	public String getPartsRemark() {
		return partsRemark;
	}
	public void setPartsRemark(String partsRemark) {
		this.partsRemark = partsRemark;
	}
	
}
