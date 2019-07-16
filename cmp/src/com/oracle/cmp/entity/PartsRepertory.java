package com.oracle.cmp.entity;

public class PartsRepertory {
	private int partsRepId;
	private Parts parts;
	private int partsRepCount;
	
	public int getPartsRepCount() {
		return partsRepCount;
	}
	public void setPartsRepCount(int partsRepCount) {
		this.partsRepCount = partsRepCount;
	}
	public int getPartsRepId() {
		return partsRepId;
	}
	public void setPartsRepId(int partsRepId) {
		this.partsRepId = partsRepId;
	}
	public Parts getParts() {
		return parts;
	}
	public void setParts(Parts parts) {
		this.parts = parts;
	}
	
	
}
