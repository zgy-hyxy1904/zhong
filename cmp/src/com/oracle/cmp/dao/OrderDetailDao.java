package com.oracle.cmp.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.oracle.cmp.entity.Order;
import com.oracle.cmp.entity.OrderDetail;
import com.oracle.cmp.entity.Parts;

public class OrderDetailDao extends BaseDao implements IDao<OrderDetail, Integer> {

	@Override
	public void insert(OrderDetail entity) {
		SqlSession sqlSession = this.getSqlSession();
		sqlSession.insert("OrderDetail.insert",entity);
		sqlSession.commit();
		sqlSession.close();	
	}

	@Override
	public void update(OrderDetail entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Integer id) {
		SqlSession sqlSession = this.getSqlSession();
		sqlSession.delete("OrderDetail.delete",id);
		sqlSession.commit();
		sqlSession.close();	
		
	}

	@Override
	public List<OrderDetail> selectForPage(Map<String, Object> map, int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<OrderDetail> select(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public OrderDetail selectById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int count(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return 0;
	}
	public static void main(String[] args) {
		OrderDetailDao dao = new OrderDetailDao();
		dao.selectById(1);
	}
}
