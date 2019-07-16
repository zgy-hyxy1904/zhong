package com.oracle.cmp.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.oracle.cmp.common.Common;
import com.oracle.cmp.entity.Parts;
import com.oracle.cmp.entity.User;
import com.oracle.cmp.service.PartsService;

public class PartsDao extends BaseDao implements IDao<Parts, Integer> {

	@Override
	public void insert(Parts entity) {
		SqlSession sqlSession = this.getSqlSession();
		sqlSession.insert("Parts.insert",entity);
		sqlSession.commit();
		sqlSession.close();
	}

	@Override
	public void update(Parts entity) {
		SqlSession sqlSession = this.getSqlSession();
		sqlSession.update("Parts.update",entity);
		sqlSession.commit();
		sqlSession.close();
		
	}

	@Override
	public void delete(Integer id) {
		SqlSession sqlSession = this.getSqlSession();
		sqlSession.delete("Parts.delete",id);
		sqlSession.commit();
		sqlSession.close();

	}

	@Override
	public List<Parts> selectForPage(Map<String, Object> map, int pageNo, int pageSize) {
		SqlSession sqlSession = this.getSqlSession();
		map.put("index", (pageNo-1)*pageSize);
		map.put("size", pageSize);
		List<Parts> list = sqlSession.selectList("Parts.select",map);
		sqlSession.close();
		return list;
	}

	@Override
	public List<Parts> select(Map<String, Object> map) {
		SqlSession sqlSession = this.getSqlSession();
		List<Parts> list = sqlSession.selectList("Parts.selectAll",map);
		sqlSession.close();
		return list;
	}

	@Override
	public Parts selectById(Integer id) {
		SqlSession sqlSession = this.getSqlSession();
		Parts parts = sqlSession.selectOne("Parts.selectById",id);
		sqlSession.close();
		return parts;
	}

	@Override
	public int count(Map<String, Object> map) {
		SqlSession sqlSession = this.getSqlSession();
		int count = sqlSession.selectOne("Parts.count",map);
		sqlSession.close();
		return count;
	}
}
