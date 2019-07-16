package com.oracle.cmp.entity;

public class OrderDetail {
	private int orderDetailId;
	private Parts parts;
	private Order order;
	private int orderPartsCount;
	public Parts getParts() {
		return parts;
	}
	public void setParts(Parts parts) {
		this.parts = parts;
	}
	public int getOrderDetailId() {
		return orderDetailId;
	}
	public void setOrderDetailId(int orderDetailId) {
		this.orderDetailId = orderDetailId;
	}
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	public int getOrderPartsCount() {
		return orderPartsCount;
	}
	public void setOrderPartsCount(int orderPartsCount) {
		this.orderPartsCount = orderPartsCount;
	}
	
}
