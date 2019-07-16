package com.oracle.cmp.dao;

import java.util.List;
import java.util.Map;

public interface IDao<Entity,PK> {
	public void insert(Entity entity);
	public void update(Entity entity);
	public void delete(PK id);
	public List<Entity> selectForPage(Map<String,Object> map,int pageNo,int pageSize);
	public List<Entity> select(Map<String,Object> map);
	public Entity selectById(PK id);
	public int count(Map<String,Object> map);
}
