package com.oracle.cmp.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oracle.cmp.common.Common;
import com.oracle.cmp.common.MD5Util;
import com.oracle.cmp.dao.UserDao;
import com.oracle.cmp.entity.Emp;
import com.oracle.cmp.entity.User;
import com.oracle.cmp.service.EmpService;
import com.oracle.cmp.service.UserService;

@WebServlet("/controller/UserController")
public class UserController extends HttpServlet {
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
		}else if("view".equals(flag)) {
			view(request,response);
		}
	}
	
	protected void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		EmpService empService = new EmpService();
		List<Emp> list = empService.queryNoRegist();
		request.setAttribute("empList", list);
		request.getRequestDispatcher("/page/system/user/useradd.jsp").forward(request, response);
	}
	protected void save(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String loginName = request.getParameter("loginName");
		String loginPwd = request.getParameter("loginPwd");
		String eid = request.getParameter("eid");
		UserService userService = new UserService();
		User user = new User();
		user.setLoginName(loginName);
		user.setLoginPwd(loginPwd);
		Emp e = new Emp();
		e.setId(Integer.parseInt(eid));
		user.setE(e);
		userService.save(user);
		request.getRequestDispatcher("/controller/user?flag=query").forward(request, response);
	}
	protected void query(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String loginName = request.getParameter("q_loginName");
		String name = request.getParameter("name");
		String pageNo = request.getParameter("pageNo");
		pageNo=pageNo==null?"1":pageNo;
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("loginName", loginName);
		map.put("name", name);
		UserService userService = new UserService();
		int count = userService.count(map);
		int sum = (count+Common.PAGESIZE-1)/Common.PAGESIZE;
		List<User> list = userService.query(map, Integer.parseInt(pageNo), Common.PAGESIZE);
		request.setAttribute("loginName", loginName);
		request.setAttribute("name", name);
		request.setAttribute("pageNo", pageNo);
		request.setAttribute("sum", sum);
		request.setAttribute("list", list);
		
		request.getRequestDispatcher("/page/system/user/userlist.jsp").forward(request, response);
	}
	protected void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userId = request.getParameter("userId");
		UserService userService = new UserService();
		userService.delete(Integer.parseInt(userId));
		request.getRequestDispatcher("/controller/user?flag=query").forward(request, response);
	}
	protected void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userId = request.getParameter("userId");
		UserService userService = new UserService();
		User user = userService.queryOne(Integer.parseInt(userId));
		request.setAttribute("user", user);
		request.getRequestDispatcher("/page/system/user/useredit.jsp").forward(request, response);
	}
	protected void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String loginPwd = request.getParameter("loginPwd");
		String reloginPwd = request.getParameter("reloginPwd");
		String userId = request.getParameter("userId");
		if(!loginPwd.equals(reloginPwd)) {
			request.setAttribute("msg", "涓ゆ杈撳叆鐨勫瘑鐮佷笉涓�鑷�");
			request.getRequestDispatcher("/page/system/user/useredit.jsp").forward(request, response);
			return;
		}
		UserService userService = new UserService();
		User user = new User();
		user.setUserId(Integer.parseInt(userId));
		user.setLoginPwd(MD5Util.getMD5Code(loginPwd));
		userService.update(user);
		request.getRequestDispatcher("/controller/user?flag=query").forward(request, response);
	}
	protected void view(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
}
