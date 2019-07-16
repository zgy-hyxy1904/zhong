package com.oracle.cmp.common;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
@WebFilter(urlPatterns={"*.jsp","/controller/*"})
public class CmpFilter implements Filter{

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse res = (HttpServletResponse)response;
		//����������Ӧ�ַ���
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		//������������
		res.setHeader("Pragma","No-cache"); 
		res.setHeader("Cache-Control","no-cache"); 
		res.setDateHeader("Expires", 0); 
		//��ֹ�Ƿ���¼
		HttpSession session = req.getSession();
		String path = req.getServletPath();
		if(!path.equals("/login.jsp")&&!path.equals("/controller/login")) {
			if(session.getAttribute("user")==null) {
				res.sendRedirect(req.getContextPath()+"/login.jsp");
				return;
			}
		}
		
		//���ݼ���
		chain.doFilter(req, res);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
