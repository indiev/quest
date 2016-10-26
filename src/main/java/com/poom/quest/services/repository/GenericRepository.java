package com.poom.quest.services.repository;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.poom.quest.services.model.Code;
import com.poom.quest.services.model.Model;
import com.poom.quest.util.reflect.Reflect;

@Repository
public abstract class GenericRepository<T, ID extends Serializable> {

	static private final String REGION = "services";
	
	@PersistenceContext(unitName = "entityManagerUnit")
	protected EntityManager em;
	
    @SuppressWarnings("unchecked")
	protected final Class<T> domainClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	private final String model = domainClass.getSimpleName();
	protected String SELECT_ALL_SQL = "SELECT * FROM " + this.model;
	private String SELECT_COUNT_SQL = "SELECT count(*) FROM " + this.model;
	
	@Transactional
	public T add(T entity) {
		em.persist(entity);
		return entity;
	}
	
	public T get(ID id) {
		return em.find(domainClass, id);
	}
	
	public T getByKey(String keyName, String key) {
		String where = " WHERE " + keyName + "=:key";
		List<T> list = em.createNativeQuery(SELECT_ALL_SQL + where, domainClass).setParameter("key", key).getResultList();
		return (list.isEmpty())?null:list.get(0);
	}
	
	public T getByKeys(Map<String, String> keys) {
		String where = " WHERE 1=1";
		for(Entry<String, String> entry : keys.entrySet()) where += " AND " + entry.getKey() + "=:" + entry.getKey();
		Query query = em.createNativeQuery(SELECT_ALL_SQL + where, domainClass);
		for(Entry<String, String> entry : keys.entrySet()) query.setParameter(entry.getKey(), entry.getValue());
		List<T> list = query.getResultList();
		return (list.isEmpty())?null:list.get(0);
	}
	
	public List<T> list() {
		return em.createNativeQuery(SELECT_ALL_SQL, domainClass).getResultList();
	}
	
	public List<T> listByKeyId(String keyName, ID key) {
		return this.listByKey(keyName + "Id", key);
	}
	
	public List<T> listByKeyId(String keyName, String key) {
		return this.listByKey(keyName + "Id", key);
	}
	
	public List<T> listByKey(String keyName, ID key) {
		if(key != null) {
			String where = " WHERE " + keyName + "=:key";
			return em.createNativeQuery(SELECT_ALL_SQL + where, domainClass).setParameter("key", key).getResultList();			
		} else {
			String where = " WHERE " + keyName + " is NULL";
			return em.createNativeQuery(SELECT_ALL_SQL + where, domainClass).getResultList();
		}
	}
	
	public List<T> listByKey(String keyName, String key) {
		if(key != null) {
			String where = " WHERE " + keyName + "=:key";
			return em.createNativeQuery(SELECT_ALL_SQL + where, domainClass).setParameter("key", key).getResultList();
		} else {
			String where = " WHERE " + keyName + " is NULL";
			return em.createNativeQuery(SELECT_ALL_SQL + where, domainClass).getResultList();
		}
	}
	
	public List<T> listByKeys(Map<String, Object> params) {
		String where = " WHERE 1=1";
		for(String key : params.keySet()) {
			Field field = Reflect.getField(domainClass, key);
			if(field == null) { //검색되면 안되는 값. 필터링 해야 됨
				params.remove(key);
				continue; 
			}
			if(Model.class.isAssignableFrom(field.getType())) { //Type이 모델이라면
				if(Code.class.isAssignableFrom(field.getType())) {	//Type이 Code일 경우
					//params.put(key, Arrays.asList(key.split(",")));
					params.put(key, Arrays.asList(((String)params.get(key)).split(",")));
					where += " AND " + key + "Id IN (SELECT id FROM Code WHERE model='" + model + "' AND attribute='" + key + "' AND value IN :" + key +")";
				} else  where += " AND " + key + "Id=:" + key;
			} else if(Set.class.isAssignableFrom(field.getType())) { //Type이 List일 경우
				String keyModel = key.substring(0, 1).toUpperCase() + key.substring(1, key.length()-1);
				String keyId = keyModel + "Id";
				if(field.getAnnotation(ManyToMany.class) != null) {
					String table = null;
					table = ((JoinTable)field.getAnnotation(JoinTable.class)).name();
					where += " AND id in (SELECT " + model + "Id FROM " + table + " WHERE " + keyId + "=:" + key + ")";
				} else { //onetomany
					// join keyModel on model.key = keyModel.key
					//id in (select modelId from table where key=:key)
					//where += " AND id in (SELECT " + model + "Id FROM " + keyModel + " WHERE " + id + "=:key)";
				}
			} else if(String.class.isAssignableFrom(field.getType())){ //문자열 검색
				params.put(key, ("%"+params.get(key)+"%").toLowerCase());
				where += " AND LOWER(" + key + ") LIKE :" + key +"";
			/*} else if(Integer.class.isAssignableFrom(field.getType())){ //숫자 검색
				;
			} else if(Date.class.isAssignableFrom(field.getType())){ //날짜 검색
				;*/
			} else where += " AND " + key + "=:" + key;
			System.out.println(key);
		}
		Query query = em.createNativeQuery(SELECT_ALL_SQL + where, domainClass);
		System.out.println(where);
		for(String key : params.keySet()) query.setParameter(key, params.get(key));
		return query.getResultList();
	}
	
	public List<T> listByParent(ID parentId, Class<?> parentClass) {
		String parentName = parentClass.getSimpleName();
		if(parentClass.equals(domainClass)) parentName = "parent";
		String columnName = parentName.toLowerCase() + "Id";
		if(parentId != null) {
			String where = " WHERE " + columnName + "=:" + columnName;
			return em.createNativeQuery(SELECT_ALL_SQL + where, domainClass).setParameter(columnName, parentId).getResultList();
		} else {
			String where = " WHERE " + columnName + " is NULL";
			return em.createNativeQuery(SELECT_ALL_SQL + where, domainClass).getResultList();
		}
	}
	
	public List<T> listByParent(ID parentId, String parentName) {
		String columnName = parentName.toLowerCase() + "Id";
		if(parentId != null) {
			String where = " WHERE " + columnName + "=:" + columnName;
			return em.createNativeQuery(SELECT_ALL_SQL + where, domainClass).setParameter(columnName, parentId).getResultList();
		} else {
			String where = " WHERE " + columnName + " is NULL";
			return em.createNativeQuery(SELECT_ALL_SQL + where, domainClass).getResultList();
		}
	}
	
	public List<T> search(String keyword, String[] keys) {
		keyword = ("%" + keyword + "%").toLowerCase();
		String where = " WHERE 1=1";
		if(keys == null || keys.length == 0) keys = new String[]{"name"};
		for (String key : keys) where += " AND LOWER(" + key + ") LIKE :keyword";
		return em.createNativeQuery(SELECT_ALL_SQL + where, domainClass).setParameter("keyword", keyword).getResultList();
	}
	
	public List<T> search(String keyword, String[] keys, ID userId) {
		keyword = ("%" + keyword + "%").toLowerCase();
		String where = " WHERE 1=1";
		if(keys == null || keys.length == 0) keys = new String[]{"name"};
		for (String key : keys) where += " AND LOWER(" + key + ") LIKE :keyword";
		where += " AND userId = :userId";
		return em.createNativeQuery(SELECT_ALL_SQL + where, domainClass).setParameter("keyword", keyword).setParameter("userId", userId).getResultList();
	}
	
	@Transactional
	public T update(T entity) {
		return em.merge(entity);
	}
	 
	public long count() {
		return em.createNamedQuery(SELECT_COUNT_SQL, Long.class).getSingleResult();
	}
	
	@Transactional
	public void delete(ID id) {
		em.remove(this.get(id));
	}
}
