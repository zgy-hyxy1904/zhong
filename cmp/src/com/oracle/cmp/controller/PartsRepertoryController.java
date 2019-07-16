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
import com.oracle.cmp.entity.Parts;
import com.oracle.cmp.entity.PartsRepertory;
import com.oracle.cmp.entity.User;
import com.oracle.cmp.service.PartsRepertoryService;
import com.oracle.cmp.service.UserService;


@WebServlet("/controller/PartsRepertoryController")
public class PartsRepertoryController extends HttpServlet {
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

	private void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		// TODO Auto-generated method stub
		
	}

	private void save(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		// TODO Auto-generated method stub
		
	}

	private void query(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String partsId = request.getParameter("partsId");
		System.out.println(partsId);
		String partsName = request.getParameter("partsName");
		String pageNo = request.getParameter("pageNo");
		pageNo=pageNo==null?"1":pageNo;
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("partsId", partsId);
		map.put("partsName", partsName);
		PartsRepertoryService partsRepertoryService = new PartsRepertoryService();
		int count = partsRepertoryService.count(map);
		int index = 1;
		int sum = (count+Common.PAGESIZE-1)/Common.PAGESIZE;
		List<PartsRepertory> list = partsRepertoryService.query(map, Integer.parseInt(pageNo), Common.PAGESIZE);
		request.setAttribute("partsId", partsId);
		request.setAttribute("partsName", partsName);
		request.setAttribute("pageNo", pageNo);
		request.setAttribute("sum", sum);
		request.setAttribute("index", index);
		request.setAttribute("list", list);
		//ÇëÇó×ª·¢
		request.getRequestDispatcher("/page/partssys/partsrep/partsreplist.jsp").forward(request, response);
		
	}
}
