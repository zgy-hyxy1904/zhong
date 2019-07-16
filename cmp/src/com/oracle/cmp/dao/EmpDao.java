package com.oracle.cmp.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.oracle.cmp.entity.Emp;

public class EmpDao extends BaseDao implements IDao<Emp,Integer>{

	@Override
	public void insert(Emp entity) {
		
	}

	@Override
	public void update(Emp entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Emp> selectForPage(Map<String, Object> map, int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Emp> select(Map<String, Object> map) {
		SqlSession sqlSession = this.getSqlSession();
		List<Emp> list = sqlSession.selectList("Emp.select", null);
		sqlSession.close();
		return list;
	}

	@Override
	public Emp selectById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int count(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return 0;
	}

	public List<Emp> selectNoRegist() {
		SqlSession sqlSession = this.getSqlSession();
		List<Emp> list = sqlSession.selectList("Emp.selectNoRegist", null);
		sqlSession.close();
		return list;
	}
}
