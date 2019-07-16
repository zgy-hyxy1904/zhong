package com.oracle.cmp.service;

import java.util.List;
import java.util.Map;

import com.oracle.cmp.dao.CodeDao;
import com.oracle.cmp.dao.UserDao;
import com.oracle.cmp.entity.Code;
import com.oracle.cmp.entity.User;


public class CodeService {
	public List<Code> queryAll(Map<String,Object> map) {
		CodeDao dao = new CodeDao();
		return dao.select(map);
	}
}
