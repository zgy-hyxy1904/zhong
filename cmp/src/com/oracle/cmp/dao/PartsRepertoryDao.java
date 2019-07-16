package com.oracle.cmp.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.oracle.cmp.common.Common;
import com.oracle.cmp.entity.Order;
import com.oracle.cmp.entity.Parts;
import com.oracle.cmp.entity.PartsRepertory;
import com.oracle.cmp.entity.User;
import com.oracle.cmp.service.OrderService;
import com.oracle.cmp.service.PartsRepertoryService;

public class PartsRepertoryDao extends BaseDao implements IDao<PartsRepertory,Integer>{

	@Override
	public void insert(PartsRepertory entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(PartsRepertory entity) {
		SqlSession sqlSession = this.getSqlSession();
		PartsRepertory partsRepertory = sqlSession.selectOne("PartsRepertory.update",entity);
		sqlSession.close();
		
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<PartsRepertory> selectForPage(Map<String, Object> map, int pageNo, int pageSize) {
		SqlSession sqlSession = this.getSqlSession();
		map.put("index", (pageNo-1)*pageSize);
		map.put("size", pageSize);
		List<PartsRepertory> list = sqlSession.selectList("PartsRepertory.select",map);
		sqlSession.close();
		return list;
	}

	@Override
	public List<PartsRepertory> select(Map<String, Object> map) {
		SqlSession sqlSession = this.getSqlSession();
		List<PartsRepertory> list = sqlSession.selectList("PartsRepertory.selectAll",map);
		sqlSession.close();
		return list;
	}

	@Override
	public PartsRepertory selectById(Integer id) {
		SqlSession sqlSession = this.getSqlSession();
		PartsRepertory partsRepertory = sqlSession.selectOne("PartsRepertory.selectId",id);
		sqlSession.close();
		return partsRepertory;
	}

	@Override
	public int count(Map<String, Object> map) {
		SqlSession sqlSession = this.getSqlSession();
		int count = sqlSession.selectOne("PartsRepertory.count",map);
		sqlSession.close();
		return count;
	}
}
