package com.oracle.cmp.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import com.oracle.cmp.dao.OrderDao;
import com.oracle.cmp.dao.OrderDetailDao;
import com.oracle.cmp.entity.Code;
import com.oracle.cmp.entity.Order;
import com.oracle.cmp.entity.OrderDetail;
import com.oracle.cmp.entity.Parts;
import com.oracle.cmp.entity.PartsRepBill;
import com.oracle.cmp.entity.User;


public class OrderService {
	public boolean save(Order order,User user){
		String billFlag = "O";
		String billType = "out1";
		String billExplain = "订单出库操作";
		List<Boolean> list = new ArrayList<Boolean>();
		int count = 0;
		for(int i = 0;i<order.getOrderDetails().size();i++) {
			PartsRepBill partsRepBill = new PartsRepBill();
			partsRepBill.setBillflag(billFlag);
			Code code = new Code();
			code.setCode(billType);
			partsRepBill.setCode(code);
			partsRepBill.setBilltime(new Date());
			user.setUserId(user.getUserId());
			partsRepBill.setUser(user);
			Parts parts = new Parts();
			parts.setPartsId(order.getOrderDetails().get(i).getParts().getPartsId());
			partsRepBill.setParts(parts);
			partsRepBill.setBillcount(order.getOrderDetails().get(i).getOrderPartsCount());
			partsRepBill.setBillExplain(billExplain);	
			PartsRepertoryService partsRepertoryService = new PartsRepertoryService();
			boolean index = partsRepertoryService.repertory(partsRepBill);
			list.add(index);
		}
		for(Boolean b:list) {
			if(b==false) {
				count++;
			}
		}if(count==0) {
			OrderDao dao = new OrderDao();
			dao.insert(order);
			int orderId = order.getOrderId();
			for(int j = 0;j<order.getOrderDetails().size();j++) {
				OrderDetail orderDetail = new OrderDetail();
				Parts parts1 = new Parts();
				parts1.setPartsId(order.getOrderDetails().get(j).getParts().getPartsId());
				orderDetail.setParts(parts1);
				Order order1 = new Order();
				order1.setOrderId(orderId);
				orderDetail.setOrder(order1);
				orderDetail.setOrderPartsCount(order.getOrderDetails().get(j).getOrderPartsCount());
				OrderDetailDao dao1 = new OrderDetailDao();
				dao1.insert(orderDetail);
			}
		}else {
			return false;
		}
		return true;
	}
	public boolean save1(Order order,User user){
		String billFlag = "O";
		String billType = "out1";
		String billExplain = "订单出库操作";
		List<Boolean> list = new ArrayList<Boolean>();
		int count = 0;
		for(int i = 0;i<order.getOrderDetails().size();i++) {
			PartsRepBill partsRepBill = new PartsRepBill();
			partsRepBill.setBillflag(billFlag);
			Code code = new Code();
			code.setCode(billType);
			partsRepBill.setCode(code);
			partsRepBill.setBilltime(new Date());
			user.setUserId(user.getUserId());
			partsRepBill.setUser(user);
			Parts parts = new Parts();
			parts.setPartsId(order.getOrderDetails().get(i).getParts().getPartsId());
			partsRepBill.setParts(parts);
			partsRepBill.setBillcount(order.getOrderDetails().get(i).getOrderPartsCount());
			partsRepBill.setBillExplain(billExplain);	
			PartsRepertoryService partsRepertoryService = new PartsRepertoryService();
			boolean index = partsRepertoryService.repertory(partsRepBill);
			list.add(index);
		}
		for(Boolean b:list) {
			if(b==false) {
				count++;
			}
		}
		if(count==0) {
			OrderDao dao = new OrderDao();
			dao.insert(order);
			return true;
		}
		return false;
	}
	public void update(Order order) {
		OrderDao dao = new OrderDao();
		dao.update(order);
	}
	public void delete(int orderId,String orderFlag1) {
		if("1".equals(orderFlag1)){
			OrderDao dao = new OrderDao();
			Order order = dao.selectById(orderId);
			List<OrderDetail> details = order.getOrderDetails();
			for(OrderDetail detail:details){
				Parts parts = detail.getParts();
				PartsRepBill pr = new PartsRepBill();
				pr.setBillflag("I");
				Code code = new Code();
				code.setCode("in3");
				pr.setCode(code);
				pr.setParts(parts);
				pr.setBillcount(detail.getOrderPartsCount());
				pr.setBilltime(new Date());
				User user = new User();
				user.setUserId(1);
				pr.setUser(user);
				pr.setBillExplain("订单入库操作");
				PartsRepertoryService partsRepertoryService = new PartsRepertoryService();
				boolean index = partsRepertoryService.repertory(pr);
				System.out.println();
			}
		}
		OrderDetailDao orderDetailDao = new OrderDetailDao();
		int a = orderId;
		orderDetailDao.delete(a);
		
		OrderDao orderDao = new OrderDao();
		orderDao.delete(orderId);
	}
	public void check(int orderId,String orderFlag) {
		if("3".equals(orderFlag)){
			OrderDao dao = new OrderDao();
			Order order = dao.selectById(orderId);
			List<OrderDetail> details = order.getOrderDetails();
			for(OrderDetail detail:details){
				Parts parts = detail.getParts();
				PartsRepBill pr = new PartsRepBill();
				pr.setBillflag("I");
				Code code = new Code();
				code.setCode("in3");
				pr.setCode(code);
				pr.setParts(parts);
				pr.setBillcount(detail.getOrderPartsCount());
				pr.setBilltime(new Date());
				User user = new User();
				user.setUserId(1);
				pr.setUser(user);
				pr.setBillExplain("订单入库操作");
				PartsRepertoryService partsRepertoryService = new PartsRepertoryService();
				boolean index = partsRepertoryService.repertory(pr);
				System.out.println();
			}
		}
		OrderDao dao = new OrderDao();
		Order order = new Order();
		order.setOrderFlag(orderFlag);
		order.setOrderId(orderId);
		dao.update(order); 
		
	}
	public List<Order> query(Map<String,Object> map,int pageNo,int pageSize) {
		OrderDao dao = new OrderDao();
		return dao.selectForPage(map,pageNo,pageSize);
	}
	public List<Order> query(Map<String,Object> map) {
		OrderDao dao = new OrderDao();
		return dao.select(map);
	}
	public Order queryOne(int id) {
		OrderDao dao = new OrderDao();
		return dao.selectById(id);
	}
	public int count(Map<String,Object> map) {
		OrderDao dao = new OrderDao();
		return dao.count(map);
	}
}
