package com.oracle.cmp.service;

import java.util.List;

import com.oracle.cmp.dao.EmpDao;
import com.oracle.cmp.entity.Emp;

public class EmpService {
	public List<Emp> query(){
		EmpDao dao = new EmpDao();
		return dao.select(null);
	}
	public List<Emp> queryNoRegist(){
		EmpDao dao = new EmpDao();
		return dao.selectNoRegist();
	}
}
