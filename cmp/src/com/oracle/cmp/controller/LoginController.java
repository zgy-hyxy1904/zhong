package com.oracle.cmp.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oracle.cmp.entity.User;
import com.oracle.cmp.service.UserService;

@WebServlet("/controller/login")
public class LoginController extends HttpServlet{
	
	@Override
	protected void service(HttpServletRequest arg0, HttpServletResponse arg1) throws ServletException, IOException {
		// TODO Auto-generated method stub
		login(arg0, arg1);
	}
	
	private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userName = request.getParameter("userName");
		String passWord = request.getParameter("passWord");		
		//passWord = MD5Util.getMD5Code(passWord);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("userName", userName);
		map.put("passWord", passWord);
		UserService UserService = new UserService();
		List<User> list = UserService.login(map);
		HttpSession session=request.getSession();
		ServletContext application =this.getServletContext();
		PrintWriter pw = response.getWriter();
		if(list.size()==1) {
			session.setAttribute("user", list.get(0));
			if(application.getAttribute(userName)!=null) {
				HttpSession session1=(HttpSession)application.getAttribute(userName);
				if(session1.getId()!=session.getId()) {
					session1.invalidate();
				}
			}
			application.setAttribute(userName, session);
			pw.print("Y");
		}else {
			pw.print("N");
		}
	}
}
