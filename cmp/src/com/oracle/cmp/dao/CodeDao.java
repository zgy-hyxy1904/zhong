package com.oracle.cmp.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.oracle.cmp.entity.Code;
import com.oracle.cmp.entity.PartsRepBill;
import com.oracle.cmp.service.CodeService;
import com.oracle.cmp.service.PartsRepBillService;

public class CodeDao extends BaseDao implements IDao<Code,Integer> {

	@Override
	public void insert(Code entity) {
		SqlSession sqlSession = this.getSqlSession();
		List<Code> list = sqlSession.selectList("PartsRepBill.insert",entity);
		sqlSession.commit();
		sqlSession.close();
	}

	@Override
	public void update(Code entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Code> selectForPage(Map<String, Object> map, int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Code> select(Map<String, Object> map) {
		SqlSession sqlSession = this.getSqlSession();
		List<Code> list = sqlSession.selectList("PartsRepBill.selectAll",map);
		sqlSession.close();
		return list;
	}

	@Override
	public Code selectById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int count(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return 0;
	}

}
