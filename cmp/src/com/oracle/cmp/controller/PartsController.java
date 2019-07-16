package com.oracle.cmp.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oracle.cmp.common.Common;
import com.oracle.cmp.common.MD5Util;
import com.oracle.cmp.entity.Emp;
import com.oracle.cmp.entity.Parts;
import com.oracle.cmp.entity.User;
import com.oracle.cmp.service.EmpService;
import com.oracle.cmp.service.PartsService;
import com.oracle.cmp.service.UserService;


@WebServlet("/controller/PartsController")
public class PartsController extends HttpServlet {
    @Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String flag = request.getParameter("flag");
		System.out.println(flag);
		if("add".equals(flag)) {
			add(request,response);
		}else if("save".equals(flag)) {
			save(request,response);
		}else if("query".equals(flag)) {
			query(request,response);
		}else if("delete".equals(flag)) {
			delete(request,response);
		}else if("edit".equals(flag)) {
			edit(request,response);
		}else if("update".equals(flag)) {
			update(request,response);
		}
	}
	protected void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/page/partssys/parts/partsadd.jsp").forward(request, response);
	}
	protected void save(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String partsName1 = request.getParameter("partsName1");
		String partsModel = request.getParameter("partsModel");
		String partsLoc = request.getParameter("partsLoc");
		String partsProDate = request.getParameter("partsProDate");
		String partsRemark = request.getParameter("partsRemark");
		PartsService partsService = new PartsService();
		Parts parts = new Parts();
		parts.setPartsName(partsName1);
		parts.setPartsModel(partsModel);
		parts.setPartsLoc(partsLoc);
		parts.setPartsRemark(partsRemark);
		parts.setPartsProDate(Common.getDate(partsProDate));
		partsService.save(parts);
		request.getRequestDispatcher("/controller/PartsController?flag=query").forward(request, response);
	}
	protected void query(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String partsName = request.getParameter("partsName");
		String pageNo = request.getParameter("pageNo");
		pageNo=pageNo==null?"1":pageNo;
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("partsName", partsName);
		PartsService partsService = new PartsService();
		int count = partsService.count(map);
		int sum = (count+Common.PAGESIZE-1)/Common.PAGESIZE;
		List<Parts> list = partsService.query(map, Integer.parseInt(pageNo), Common.PAGESIZE);
		request.setAttribute("partsName", partsName);
		request.setAttribute("pageNo", pageNo);
		request.setAttribute("sum", sum);
		request.setAttribute("list", list);
		request.getRequestDispatcher("/page/partssys/parts/partslist.jsp").forward(request, response);
	}
	protected void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String partsId = request.getParameter("partsId");
		PartsService partsService = new PartsService();
		partsService.delete(Integer.parseInt(partsId));
		request.getRequestDispatcher("/controller/PartsController?flag=query").forward(request, response);
	}
	protected void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String partsId = request.getParameter("partsId");
		PartsService partsService = new PartsService();
		Parts parts = partsService.queryOne(Integer.parseInt(partsId));
		request.setAttribute("parts", parts);
		request.getRequestDispatcher("/page/partssys/parts/partsedit.jsp").forward(request, response);
	}
	protected void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String partsId = request.getParameter("partsId");
		String partsName2 = request.getParameter("partsName2");
		String partsModel = request.getParameter("partsModel");
		String partsLoc = request.getParameter("partsLoc");
		String partsProDate = request.getParameter("partsProDate");
		String partsRemark = request.getParameter("partsRemark");
		Parts parts = new Parts();
		parts.setPartsId(Integer.parseInt(partsId));
		parts.setPartsName(partsName2);
		parts.setPartsLoc(partsLoc);
		parts.setPartsModel(partsModel);
		parts.setPartsProDate(Common.getDate(partsProDate));
		parts.setPartsRemark(partsRemark);
		PartsService partsService = new PartsService();
		partsService.update(parts);
		request.getRequestDispatcher("/controller/PartsController?flag=query").forward(request, response);
	}
	}
