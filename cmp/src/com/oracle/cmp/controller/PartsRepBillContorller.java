package com.oracle.cmp.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.StandardSocketFactory;
import com.oracle.cmp.common.Common;
import com.oracle.cmp.entity.Code;
import com.oracle.cmp.entity.Parts;
import com.oracle.cmp.entity.PartsRepBill;
import com.oracle.cmp.entity.PartsRepertory;
import com.oracle.cmp.entity.User;
import com.oracle.cmp.service.CodeService;
import com.oracle.cmp.service.PartsRepBillService;
import com.oracle.cmp.service.PartsRepertoryService;
import com.oracle.cmp.service.PartsService;
import com.oracle.cmp.service.UserService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


@WebServlet("/controller/PartsRepBillController")
public class PartsRepBillContorller extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String flag = request.getParameter("flag");
		System.out.println(flag);
		if(flag.equals("query")) {
			query(request,response);
		}else if(flag.equals("save")) {
			save(request,response);
		}else if(flag.equals("add")) {
			add(request,response);
		}
		
	}

	private void save(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String billFlag = request.getParameter("billFlag");
		String billType = request.getParameter("billType");
		String billTime = request.getParameter("billTime");
		String userId = request.getParameter("userId");
		String partsId = request.getParameter("partsId");
		String billCount = request.getParameter("billCount");
		String billExplain = request.getParameter("billExplain");
		
		PrintWriter pw = response.getWriter();
		PartsRepBill partsRepBill = new PartsRepBill();
		partsRepBill.setBillflag(billFlag);
		Code code = new Code();
		code.setCode(billType);
		partsRepBill.setCode(code);
		partsRepBill.setBilltime(Common.getDate(billTime));
		User user = new User();
		user.setUserId(Integer.parseInt(userId));
		partsRepBill.setUser(user);
		Parts parts = new Parts();
		parts.setPartsId(Integer.parseInt(partsId));
		partsRepBill.setParts(parts);
		partsRepBill.setBillcount(Integer.parseInt(billCount));
		partsRepBill.setBillExplain(billExplain);	
		PartsRepertoryService partsRepertoryService = new PartsRepertoryService();
		boolean index = partsRepertoryService.repertory(partsRepBill);
		if(index==false) {
			pw.write("N");
		}else if(index==true) {
			pw.write("Y");
		}	
	}

	private void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		PartsService partsService = new PartsService();
		UserService userService = new UserService();
		Map<String,Object> map = new HashMap<String,Object>();
		List<Parts> list = partsService.query(map);
		List<User> listUser = userService.query(map);
		request.setAttribute("list", list);
		request.setAttribute("listUser", listUser);
		request.getRequestDispatcher("/page/partssys/partsrep/partsrep.jsp").forward(request, response);
		
	}

	private void query(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String partsName = request.getParameter("partsName");
		String billFlag = request.getParameter("billFlag");
		String billType = request.getParameter("billType");
		String billTime1 = request.getParameter("billTime1");
		String pageNo = request.getParameter("pageNo");
		pageNo=pageNo==null?"1":pageNo;
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("partsName", partsName);
		map.put("billFlag", billFlag);
		map.put("billType", billType);
		map.put("billTime", billTime1);
		PartsRepBillService partsRepBillService = new PartsRepBillService();
		int count = partsRepBillService.count(map);
		int sum = (count+Common.PAGESIZE-1)/Common.PAGESIZE;
		List<PartsRepBill> list = partsRepBillService.query(map, Integer.parseInt(pageNo), Common.PAGESIZE);
		request.setAttribute("partsName", partsName);
		request.setAttribute("billFlag", billFlag);
		request.setAttribute("billType", billType);
		request.setAttribute("billTime1", billTime1);
		request.setAttribute("pageNo", pageNo);
		request.setAttribute("sum", sum);
		request.setAttribute("list", list);
		//ÇëÇó×ª·¢
		request.getRequestDispatcher("/page/partssys/partsrepbill/partsrepbilllist.jsp").forward(request, response);
		
	}
}
