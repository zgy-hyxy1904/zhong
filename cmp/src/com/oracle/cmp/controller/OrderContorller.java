package com.oracle.cmp.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oracle.cmp.common.Common;
import com.oracle.cmp.dao.OrderDao;
import com.oracle.cmp.entity.Code;
import com.oracle.cmp.entity.Order;
import com.oracle.cmp.entity.OrderDetail;
import com.oracle.cmp.entity.Parts;
import com.oracle.cmp.entity.PartsRepBill;
import com.oracle.cmp.entity.PartsRepertory;
import com.oracle.cmp.entity.User;
import com.oracle.cmp.service.OrderService;
import com.oracle.cmp.service.PartsRepertoryService;


@WebServlet("/contorller/OrderContorller")
public class OrderContorller extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String flag = request.getParameter("flag");
		System.out.println(flag);
		if("add".equals(flag)) {
			add(request,response);
		}else if("save".equals(flag)) {
			save(request,response);
		}else if("queryGuanLi".equals(flag)) {
			queryGuanLi(request,response);
		}else if("delete".equals(flag)) {
			delete(request,response);
		}else if("edit".equals(flag)) {
			edit(request,response);
		}else if("update".equals(flag)) {
			update(request,response);
		}else if("view".equals(flag)) {
			view(request,response);
		}else if("queryOrder".equals(flag)) {
			queryOrder(request,response);
		}else if("save1".equals(flag)) {
			save1(request,response);
		}else if("queryShenHe".equals(flag)) {
			queryShenHe(request,response);
		}else if("pass".equals(flag)) {
			pass(request,response);
		}else if("noPass".equals(flag)) {
			noPass(request,response);
		}else if("saveUpdate".equals(flag)) {
			saveUpdate(request,response);
		}else if("submitUpdate".equals(flag)) {
			submitUpdate(request,response);
		}
	}

	private void submitUpdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String orderId = request.getParameter("orderId");
		String orderCode = request.getParameter("orderCode");
		String orderDate = request.getParameter("orderDate");
		String orderFlag2 = request.getParameter("orderFlag2");
		String[] partsId = request.getParameterValues("materId");
		String[] count = request.getParameterValues("count");
		//数据装载
		PrintWriter pw = response.getWriter();
		Order order = new Order();
		order.setOrderCode(orderCode);
		order.setOrderDate(Common.getDate(orderDate));
		order.setOrderFlag(orderFlag2);
		List<OrderDetail> list = new ArrayList<OrderDetail>();
		for(int i = 0;i<partsId.length;i++) {
			OrderDetail orderDetail = new OrderDetail();
			Parts parts = new Parts();
			parts.setPartsId(Integer.parseInt(partsId[i]));
			orderDetail.setParts(parts);
			orderDetail.setOrderPartsCount(Integer.parseInt(count[i]));
			list.add(orderDetail);
		}
		order.setOrderDetails(list);
		//删除操作
		OrderService orderService = new OrderService();
		orderService.delete(Integer.parseInt(orderId),orderFlag2);
		//新增操作
		User user = new User();
		user.setUserId(1);
		boolean index = orderService.save(order, user);
		if(index==true) {
			pw.print("Y");
		}else {
			pw.print("N");
		}	
	}

	private void saveUpdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String orderId = request.getParameter("orderId");
		String orderCode = request.getParameter("orderCode");
		String orderDate = request.getParameter("orderDate");
		String orderFlag2 = request.getParameter("orderFlag2");
		String[] partsId = request.getParameterValues("materId");
		String[] count = request.getParameterValues("count");
		//数据装载
		PrintWriter pw = response.getWriter();
		Order order = new Order();
		order.setOrderCode(orderCode);
		order.setOrderDate(Common.getDate(orderDate));
		order.setOrderFlag(orderFlag2);
		List<OrderDetail> list = new ArrayList<OrderDetail>();
		for(int i = 0;i<partsId.length;i++) {
			OrderDetail orderDetail = new OrderDetail();
			Parts parts = new Parts();
			parts.setPartsId(Integer.parseInt(partsId[i]));
			orderDetail.setParts(parts);
			orderDetail.setOrderPartsCount(Integer.parseInt(count[i]));
			list.add(orderDetail);
		}
		order.setOrderDetails(list);
		//删除操作
		OrderService orderService = new OrderService();
		orderService.delete(Integer.parseInt(orderId),orderFlag2);
		//新增操作
		User user = new User();
		user.setUserId(1);
		boolean index = orderService.save1(order, user);
		if(index==true) {
			pw.print("Y");
		}else {
			pw.print("N");
		}
		
	}

	private void noPass(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String orderIdNoPass = request.getParameter("orderIdNoPass");
		System.out.println(orderIdNoPass);
		String orderFlag = "3";
		OrderService orderService = new OrderService();
		orderService.check(Integer.parseInt(orderIdNoPass), orderFlag);
		request.getRequestDispatcher("/contorller/OrderContorller?flag=queryShenHe").forward(request, response);
		
		
	}

	private void pass(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String orderIdPass = request.getParameter("orderIdPass");
		System.out.println(orderIdPass);
		String orderFlag = "2";
		Order order = new Order();
		order.setOrderId(Integer.parseInt(orderIdPass));
		order.setOrderFlag(orderFlag);
		OrderService orderService = new OrderService();
		orderService.update(order);
		request.getRequestDispatcher("/contorller/OrderContorller?flag=queryShenHe").forward(request, response);
	}

	private void queryShenHe(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String orderCode = request.getParameter("orderCode");
		String orderDate = request.getParameter("orderDate");
		String orderFlag = request.getParameter("orderFlag");
		String pageNo = request.getParameter("pageNo");
		pageNo=pageNo==null?"1":pageNo;
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("orderCode", orderCode);
		map.put("orderDate", orderDate);
		map.put("orderFlag", orderFlag);
		OrderService orderService = new OrderService();
		int count = orderService.count(map);
		int sum = (count+Common.PAGESIZE-1)/Common.PAGESIZE;
		List<Order> list = orderService.query(map, Integer.parseInt(pageNo), Common.PAGESIZE);
		request.setAttribute("orderCode", orderCode);
		request.setAttribute("orderDate", orderDate);
		request.setAttribute("orderFlag", orderFlag);
		request.setAttribute("pageNo", pageNo);
		request.setAttribute("sum", sum);
		request.setAttribute("list", list);
		request.getRequestDispatcher("/page/ordersys/order/orderchecklist.jsp").forward(request, response);
		
	}

	private void save1(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String orderCode = request.getParameter("orderCode");
		String orderDate = request.getParameter("orderDate");
		String orderFlag = request.getParameter("orderFlag");
		String[] partsId = request.getParameterValues("materId");
		String[] count = request.getParameterValues("count");
		PrintWriter pw = response.getWriter();
		Order order = new Order();
		order.setOrderCode(orderCode);
		order.setOrderDate(Common.getDate(orderDate));
		order.setOrderFlag(orderFlag);
		List<OrderDetail> list = new ArrayList<OrderDetail>();
		for(int i = 0;i<partsId.length;i++) {
			OrderDetail orderDetail = new OrderDetail();
			Parts parts = new Parts();
			parts.setPartsId(Integer.parseInt(partsId[i]));
			orderDetail.setParts(parts);
			orderDetail.setOrderPartsCount(Integer.parseInt(count[i]));
			list.add(orderDetail);
		}
		order.setOrderDetails(list);
		OrderService orderService = new OrderService();
		User user = new User();
		user.setUserId(1);
		boolean index = orderService.save1(order, user);
		System.out.println("**********"+index+"**********");
		if(index==false) {
			pw.print("N");
		}else{
			pw.print("Y");
		}
		
	}

	private void queryOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String partsName = request.getParameter("partsName");
		String pageNo = request.getParameter("pageNo");
		pageNo=pageNo==null?"1":pageNo;
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("partsName", partsName);
		PartsRepertoryService partsRepertoryService = new PartsRepertoryService();
		int count = partsRepertoryService.count(map);
		int index = 1;
		int sum = (count+Common.PAGESIZE-1)/Common.PAGESIZE;
		List<PartsRepertory> list = partsRepertoryService.query(map, Integer.parseInt(pageNo), Common.PAGESIZE);
		request.setAttribute("partsName", partsName);
		request.setAttribute("pageNo", pageNo);
		request.setAttribute("sum", sum);
		request.setAttribute("index", index);
		request.setAttribute("list", list);
		//请求转发
		request.getRequestDispatcher("/page/ordersys/order/orderlist.jsp").forward(request, response);
		
	}

	private void view(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String orderId = request.getParameter("orderId");
		OrderDao dao = new OrderDao();
		dao.selectById(Integer.parseInt(orderId));
		
	}

	private void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		
	}

	private void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String orderId = request.getParameter("orderId");
		OrderService orderService = new OrderService();
		Order order = orderService.queryOne(Integer.parseInt(orderId));
		request.setAttribute("order", order);
		request.getRequestDispatcher("/page/ordersys/order/orderedit.jsp").forward(request, response);
	}

	private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String orderId = request.getParameter("orderId");
		String orderFlag1 = request.getParameter("orderFlag1");
		OrderService orderService = new OrderService();
		orderService.delete(Integer.parseInt(orderId),orderFlag1);
		request.getRequestDispatcher("/contorller/OrderContorller?flag=queryGuanLi").forward(request, response);
		
	}

	private void queryGuanLi(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String orderCode = request.getParameter("orderCode");
		String orderDate = request.getParameter("orderDate");
		String orderFlag = request.getParameter("orderFlag");
		String pageNo = request.getParameter("pageNo");
		pageNo=pageNo==null?"1":pageNo;
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("orderCode", orderCode);
		map.put("orderDate", orderDate);
		map.put("orderFlag", orderFlag);
		OrderService orderService = new OrderService();
		int count = orderService.count(map);
		int sum = (count+Common.PAGESIZE-1)/Common.PAGESIZE;
		List<Order> list = orderService.query(map, Integer.parseInt(pageNo), Common.PAGESIZE);
		request.setAttribute("orderCode", orderCode);
		request.setAttribute("orderDate", orderDate);
		request.setAttribute("orderFlag", orderFlag);
		request.setAttribute("pageNo", pageNo);
		request.setAttribute("sum", sum);
		request.setAttribute("list", list);
		request.getRequestDispatcher("/page/ordersys/order/getmater.jsp").forward(request, response);
		
	}

	private void save(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String orderCode = request.getParameter("orderCode");
		String orderDate = request.getParameter("orderDate");
		String orderFlag = request.getParameter("orderFlag");
		String[] partsId = request.getParameterValues("materId");
		String[] count = request.getParameterValues("count");
		PrintWriter pw = response.getWriter();
		Order order = new Order();
		order.setOrderCode(orderCode);
		order.setOrderDate(Common.getDate(orderDate));
		order.setOrderFlag(orderFlag);
		List<OrderDetail> list = new ArrayList<OrderDetail>();
		for(int i = 0;i<partsId.length;i++) {
			OrderDetail orderDetail = new OrderDetail();
			Parts parts = new Parts();
			parts.setPartsId(Integer.parseInt(partsId[i]));
			orderDetail.setParts(parts);
			orderDetail.setOrderPartsCount(Integer.parseInt(count[i]));
			list.add(orderDetail);
		}
		order.setOrderDetails(list);
		OrderService orderService = new OrderService();
		User user = new User();
		user.setUserId(1);
		boolean index = orderService.save(order, user);
		System.out.println("**********"+index+"**********");
		if(index==false) {
			pw.print("N");
		}else{
			pw.print("Y");
		}
	}

	private void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		// TODO Auto-generated method stub
		
	}
}
