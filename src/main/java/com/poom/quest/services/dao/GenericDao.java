package com.poom.quest.services.dao;

import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

@Repository
public abstract class GenericDao<T> {

	static private final String REGION = "services";
	
	@PersistenceContext(unitName = "localEntityManagerUnit")
	protected EntityManager em;
	
    @SuppressWarnings("unchecked")
	protected final Class<T> clazz = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	private final String model = clazz.getSimpleName();
	protected String SELECT_ALL_SQL = "SELECT * FROM " + this.model;
	private String SELECT_COUNT_SQL = "SELECT count(*) FROM " + this.model;
	
	public T add(T entity) {
		em.persist(entity);
		return entity;
	}
	
	public T get(Integer id) {
		return em.find(clazz, id);
	}
	
	public T getByKey(String keyName, String key) {
		String where = " WHERE " + keyName + "=:key";
		List<T> list = em.createNativeQuery(SELECT_ALL_SQL + where, clazz).setParameter("key", key).getResultList();
		return (list.isEmpty())?null:list.get(0);
	}
	
	public T getByKeys(Map<String, String> keys) {
		String where = " WHERE 1=1";
		for(Entry<String, String> entry : keys.entrySet()) where += " AND " + entry.getKey() + "=:" + entry.getKey();
		Query query = em.createNativeQuery(SELECT_ALL_SQL + where, clazz);
		for(Entry<String, String> entry : keys.entrySet()) query.setParameter(entry.getKey(), entry.getValue());
		List<T> list = query.getResultList();
		return (list.isEmpty())?null:list.get(0);
	}
	
	public List<T> list() {
		return em.createNativeQuery(SELECT_ALL_SQL, clazz).getResultList();
	}
	
	public List<T> listByKeyId(String keyName, Integer key) {
		return this.listByKey(keyName + "Id", key);
	}
	
	public List<T> listByKeyId(String keyName, String key) {
		return this.listByKey(keyName + "Id", key);
	}
	
	public List<T> listByKey(String keyName, Integer key) {
		if(key != null) {
			String where = " WHERE " + keyName + "=:key";
			return em.createNativeQuery(SELECT_ALL_SQL + where, clazz).setParameter("key", key).getResultList();			
		} else {
			String where = " WHERE " + keyName + " is NULL";
			return em.createNativeQuery(SELECT_ALL_SQL + where, clazz).getResultList();
		}
	}
	
	public List<T> listByKey(String keyName, String key) {
		if(key != null) {
			String where = " WHERE " + keyName + "=:key";
			return em.createNativeQuery(SELECT_ALL_SQL + where, clazz).setParameter("key", key).getResultList();
		} else {
			String where = " WHERE " + keyName + " is NULL";
			return em.createNativeQuery(SELECT_ALL_SQL + where, clazz).getResultList();
		}
	}
	
	public List<T> listByKeys(Map<String, Object> keys) {
		String where = " WHERE 1=1";
		for(Entry<String, Object> entry : keys.entrySet()) where += " AND " + entry.getKey() + "=:" + entry.getKey();
		Query query = em.createNativeQuery(SELECT_ALL_SQL + where, clazz);
		for(Entry<String, Object> entry : keys.entrySet()) query.setParameter(entry.getKey(), entry.getValue());
		return query.getResultList();
	}
	
	public List<T> listByParent(Integer parentId, Class<?> parentClass) {
		String parentName = parentClass.getSimpleName();
		String columnName = parentName.toLowerCase() + "Id";
		if(parentId != null) {
			String where = " WHERE " + columnName + "=:" + columnName;
			return em.createNativeQuery(SELECT_ALL_SQL + where, clazz).setParameter(columnName, parentId).getResultList();
		} else {
			String where = " WHERE " + columnName + " is NULL";
			return em.createNativeQuery(SELECT_ALL_SQL + where, clazz).getResultList();
		}
	}
	
	public List<T> listByParent(Integer parentId, String parentName) {
		String columnName = parentName.toLowerCase() + "Id";
		if(parentId != null) {
			String where = " WHERE " + columnName + "=:" + columnName;
			return em.createNativeQuery(SELECT_ALL_SQL + where, clazz).setParameter(columnName, parentId).getResultList();
		} else {
			String where = " WHERE " + columnName + " is NULL";
			return em.createNativeQuery(SELECT_ALL_SQL + where, clazz).getResultList();
		}
	}
	
	public List<T> search(String keyword, String[] keys) {
		keyword = ("%" + keyword + "%").toLowerCase();
		String where = " WHERE ";
		if(keys.length == 0) keys = new String[]{"name"};
		for (int i=0; i<keys.length; i++) {
			if(i !=0 ) where += " AND ";
			where += "LOWER(" + keys[i] + ") LIKE :keyword";
		}
		return em.createNativeQuery(SELECT_ALL_SQL + where, clazz).setParameter("keyword", keyword).getResultList();
	}
	
	public T update(T entity) {
		return em.merge(entity);
	}
	 
	public long count() {
		return em.createNamedQuery(SELECT_COUNT_SQL, Long.class).getSingleResult();
	}
	
	public void delete(Integer id) {
		em.remove(this.get(id));
	}
}
