package com.oracle.cmp.service;

import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import com.oracle.cmp.dao.UserDao;
import com.oracle.cmp.entity.User;

public class UserService {
	public void save(User user) {
		UserDao dao = new UserDao();
		dao.insert(user);
	}
	public void update(User user) {
		UserDao dao = new UserDao();
		dao.update(user);
	}
	public void delete(int id) {
		UserDao dao = new UserDao();
		dao.delete(id);
	}
	public List<User> query(Map<String,Object> map,int pageNo,int pageSize) {
		UserDao dao = new UserDao();
		return dao.selectForPage(map,pageNo,pageSize);
	}
	public List<User> query(Map<String,Object> map) {
		UserDao dao = new UserDao();
		return dao.select(map);
	}
	public User queryOne(int id) {
		UserDao dao = new UserDao();
		return dao.selectById(id);
	}
	public int count(Map<String,Object> map) {
		UserDao dao = new UserDao();
		return dao.count(map);
	}
	public List<User> login(Map<String,Object> map) {
		UserDao dao = new UserDao();
		return dao.login(map);
	}
}
