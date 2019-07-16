package com.oracle.cmp.service;

import java.util.List;
import java.util.Map;

import com.oracle.cmp.dao.PartsDao;
import com.oracle.cmp.entity.Parts;

public class PartsService {
	public void save(Parts parts) {
		PartsDao dao = new PartsDao();
		dao.insert(parts);
	}
	public void update(Parts parts) {
		PartsDao dao = new PartsDao();
		dao.update(parts);
	}
	public void delete(int id) {
		PartsDao dao = new PartsDao();
		dao.delete(id);
	}
	public List<Parts> query(Map<String,Object> map,int pageNo,int pageSize) {
		PartsDao dao = new PartsDao();
		return dao.selectForPage(map,pageNo,pageSize);
	}
	public List<Parts> query(Map<String,Object> map) {
		PartsDao dao = new PartsDao();
		return dao.select(map);
	}
	public Parts queryOne(int id) {
		PartsDao dao = new PartsDao();
		return dao.selectById(id);
	}
	public int count(Map<String,Object> map) {
		PartsDao dao = new PartsDao();
		return dao.count(map);
	}
}
