package com.oracle.cmp.dao;


import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.oracle.cmp.entity.Code;
import com.oracle.cmp.entity.PartsRepBill;
import com.oracle.cmp.service.PartsRepBillService;




public class PartsRepBillDao extends BaseDao implements IDao<PartsRepBill,Integer>{

	@Override
	public void insert(PartsRepBill entity) {
		SqlSession sqlSession = this.getSqlSession();
		sqlSession.selectOne("PartsRepBill.insert",entity);
		sqlSession.close();	
		
	}

	@Override
	public void update(PartsRepBill entity) {
		
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<PartsRepBill> selectForPage(Map<String, Object> map, int pageNo, int pageSize) {
		SqlSession sqlSession = this.getSqlSession();
		map.put("index", (pageNo-1)*pageSize);
		map.put("size", pageSize);
		List<PartsRepBill> list = sqlSession.selectList("PartsRepBill.select",map);
		sqlSession.close();
		return list;
	}

	@Override
	public List<PartsRepBill> select(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PartsRepBill selectById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int count(Map<String, Object> map) {
		SqlSession sqlSession = this.getSqlSession();
		int count = sqlSession.selectOne("PartsRepBill.count",map);
		sqlSession.close();
		return count;
	}
	
	

}
