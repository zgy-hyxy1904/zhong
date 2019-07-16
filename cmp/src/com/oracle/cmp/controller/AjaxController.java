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

import com.oracle.cmp.entity.Code;
import com.oracle.cmp.service.CodeService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


@WebServlet("/controller/AjaxController")
public class AjaxController extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String flag = request.getParameter("flag");
		System.out.println(flag);
		if(flag.equals("queryType")) {
			queryType(request,response);
		}
	}

	private void queryType(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String billFlag = request.getParameter("id");
		System.out.println(billFlag);
		if(billFlag.equals("O")) {
			billFlag = "out";
		}else if(billFlag.equals("I")) {
			billFlag = "in";
		}
		Map<String,Object> map = new HashMap<String,Object>();
		PrintWriter pw = response.getWriter();	
		map.put("billFlag", billFlag);
		CodeService codeService = new CodeService();
		List<Code> list = codeService.queryAll(map);
		JSONArray arr = new JSONArray();
		for(Code c:list) {
			JSONObject obj = new JSONObject();
			obj.put("id",c.getCode());
			obj.put("name", c.getName());
			arr.add(obj);
		}
		System.out.println(arr.toString());
		pw.print(arr);
		
	}

}
