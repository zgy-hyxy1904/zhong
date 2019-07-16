package com.oracle.cmp.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.oracle.cmp.common.Common;
import com.oracle.cmp.entity.Order;
import com.oracle.cmp.entity.OrderDetail;
import com.oracle.cmp.entity.Parts;
import com.oracle.cmp.service.OrderService;

public class OrderDao extends BaseDao implements IDao<Order,Integer>{

	@Override
	public void insert(Order entity) {
		SqlSession sqlSession = this.getSqlSession();
		sqlSession.insert("Order.insert",entity);
		sqlSession.commit();
		sqlSession.close();
		
	}

	@Override
	public void update(Order entity) {
		SqlSession sqlSession = this.getSqlSession();
		sqlSession.update("Order.update",entity);
		sqlSession.commit();
		sqlSession.close();
		
	}

	@Override
	public void delete(Integer id) {
		SqlSession sqlSession = this.getSqlSession();
		sqlSession.delete("Order.delete",id);
		sqlSession.commit();
		sqlSession.close();
		
	}

	@Override
	public List<Order> selectForPage(Map<String, Object> map, int pageNo, int pageSize) {
		SqlSession sqlSession = this.getSqlSession();
		map.put("index", (pageNo-1)*pageSize);
		map.put("size", pageSize);
		List<Order> list = sqlSession.selectList("Order.select",map);
		sqlSession.close();
		return list;
	}

	@Override
	public List<Order> select(Map<String, Object> map) {
		SqlSession sqlSession = this.getSqlSession();
		List<Order> list = sqlSession.selectList("Order.selectAll",map);
		sqlSession.close();
		return list;
	}

	@Override
	public Order selectById(Integer id) {
		SqlSession sqlSession = this.getSqlSession();
		Order order = sqlSession.selectOne("Order.selectById",id);
		sqlSession.close();
		return order;
	}

	@Override
	public int count(Map<String, Object> map) {
		SqlSession sqlSession = this.getSqlSession();
		int count = sqlSession.selectOne("Order.count",map);
		sqlSession.close();
		return count;
	}
}
