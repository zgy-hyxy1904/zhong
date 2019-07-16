package com.oracle.cmp.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.oracle.cmp.entity.User;

public class UserDao extends BaseDao implements IDao<User,Integer>{

	@Override
	public void insert(User entity) {
		SqlSession sqlSession = this.getSqlSession();
		sqlSession.insert("User.insert",entity);
		sqlSession.commit();
		sqlSession.close();
	}

	@Override
	public void update(User entity) {
		SqlSession sqlSession = this.getSqlSession();
		sqlSession.update("User.update",entity);
		sqlSession.commit();
		sqlSession.close();
	}

	@Override
	public void delete(Integer id) {
		SqlSession sqlSession = this.getSqlSession();
		sqlSession.delete("User.delete",id);
		sqlSession.commit();
		sqlSession.close();
	}

	@Override
	public List<User> selectForPage(Map<String, Object> map, int pageNo, int pageSize) {
		SqlSession sqlSession = this.getSqlSession();
		map.put("index", (pageNo-1)*pageSize);
		map.put("pageSize", pageSize);
		List<User> list = sqlSession.selectList("User.select",map);
		sqlSession.close();
		return list;
	}

	@Override
	public List<User> select(Map<String, Object> map) {
		SqlSession sqlSession = this.getSqlSession();
		List<User> list = sqlSession.selectList("User.selectAll",map);
		sqlSession.close();
		return list;
	}

	@Override
	public User selectById(Integer id) {
		SqlSession sqlSession = this.getSqlSession();
		User user = sqlSession.selectOne("User.selectById",id);
		sqlSession.close();
		return user;
	}

	@Override
	public int count(Map<String, Object> map) {
		SqlSession sqlSession = this.getSqlSession();
		int count = sqlSession.selectOne("User.count",map);
		sqlSession.close();
		return count;
	}
	
	public List<User> login(Map<String, Object> map) {
		SqlSession sqlSession = this.getSqlSession();
		List<User> list = sqlSession.selectList("User.login",map);
		sqlSession.close();
		return list;
	}

}
