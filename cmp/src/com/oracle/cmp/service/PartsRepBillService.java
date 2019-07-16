package com.oracle.cmp.service;

import java.util.List;
import java.util.Map;

import com.oracle.cmp.dao.PartsRepBillDao;
import com.oracle.cmp.entity.Code;
import com.oracle.cmp.entity.PartsRepBill;

public class PartsRepBillService {
	public List<PartsRepBill> query(Map<String,Object> map,int pageNo,int pageSize) {
		PartsRepBillDao dao = new PartsRepBillDao();
		return dao.selectForPage(map,pageNo,pageSize);
	}
	public int count(Map<String,Object> map) {
		PartsRepBillDao dao = new PartsRepBillDao();
		return dao.count(map);
	}
	public List<PartsRepBill> queryAll(Map<String,Object> map) {
		PartsRepBillDao dao = new PartsRepBillDao();
		return dao.select(map);
	}
	public void insert(PartsRepBill partsRepBill) {
		PartsRepBillDao dao = new PartsRepBillDao();
		dao.insert(partsRepBill);
	}
}
