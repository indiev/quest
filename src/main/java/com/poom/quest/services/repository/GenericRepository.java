package com.poom.quest.services.repository;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
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
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.support.Querydsl;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.poom.quest.services.model.Code;
import com.poom.quest.services.model.Model;
import com.poom.quest.services.model.QCode;
import com.poom.quest.services.model.abstractModel.Domain;
import com.poom.quest.util.reflect.Reflect;
import com.querydsl.core.types.EntityPath;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.JPQLQuery;

@Repository
public abstract class GenericRepository<T extends Domain, ID extends Serializable> {

	static private final String REGION = "services";
	
	protected EntityManager em;
	
	protected final Class<T> domainClass;
	private final String modelName;
	protected final String SELECT_ALL_SQL;
	protected final String SELECT_COUNT_SQL;
	
	protected final PathBuilder<T> entityPath;
	protected Querydsl querydsl;
	
	public GenericRepository() {
		domainClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
		modelName = domainClass.getSimpleName();
		SELECT_ALL_SQL = "SELECT * FROM " + this.modelName;
		SELECT_COUNT_SQL = "SELECT count(*) FROM " + this.modelName;
		
		entityPath = new PathBuilder<>(domainClass, modelName);
	}
	
	@PersistenceContext(unitName = "entityManagerUnit")
	public void setEntityManager(EntityManager entityManager) {
		Assert.notNull(entityManager);
		this.querydsl = new Querydsl(entityManager, entityPath);
		this.em = entityManager;
	}
	
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
	
	protected CriteriaQuery<T> setSort(Pageable pageable, CriteriaQuery<T> criteriaQuery, Root<T> model) {
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		List<Order> orders = new ArrayList<>();
		for(Sort.Order sortOrder : pageable.getSort())
			if(sortOrder.getDirection().equals(Sort.Direction.ASC))
				orders.add(criteriaBuilder.asc(model.get(sortOrder.getProperty())));
			else
				orders.add(criteriaBuilder.desc(model.get(sortOrder.getProperty())));
		criteriaQuery.orderBy(orders);
		return criteriaQuery;
	}
	
	protected Query setPageable(Pageable pageable, Query query) {
		return query.setMaxResults(pageable.getPageSize())
				.setFirstResult(pageable.getOffset());
	}
	
	public List<T> list(Pageable pageable) {
		return (List<T>) querydsl.applyPagination(pageable, from(entityPath)).fetch();
	}
	
	public List<T> listByKeys(Map<String, Object> params, Pageable pageable) {
		JPQLQuery<T> query = (JPQLQuery<T>) from(entityPath);
		
		for(String key : params.keySet()) {
			Field field = Reflect.getField(domainClass, key);
			if(field == null) { //검색되면 안되는 값. 필터링 해야 됨
				params.remove(key);
				continue; 
			}
			if(Model.class.isAssignableFrom(field.getType())) { //Type이 모델이라면
				if(Code.class.isAssignableFrom(field.getType())) {	//Type이 Code일 경우
					params.put(key, Arrays.asList(((String)params.get(key)).split(",")));
					QCode code = QCode.code;
					JPQLQuery<Long> subQuery = from(code).select(code.id)
						.where(code.model.eq(modelName), code.attribute.eq(key), code.value.eq(key));
					query.where(entityPath.get(key+"Id").eq(subQuery));
				} else {
					query.where(entityPath.get(key+"Id").eq(params.get(key)));
				}
			} else if(Set.class.isAssignableFrom(field.getType())) { //Type이 List일 경우
				String keyModel = key.substring(0, 1).toUpperCase() + key.substring(1, key.length()-1);
				String keyId = keyModel + "Id";
				if(field.getAnnotation(ManyToMany.class) != null) {
					/*String table = null;
					table = ((JoinTable)field.getAnnotation(JoinTable.class)).name();
					where += " AND id in (SELECT " + model + "Id FROM " + table + " WHERE " + keyId + "=:" + key + ")";*/
				} else { //onetomany
					// join keyModel on model.key = keyModel.key
					//id in (select modelId from table where key=:key)
					//where += " AND id in (SELECT " + model + "Id FROM " + keyModel + " WHERE " + id + "=:key)";
				}
			} else if(String.class.isAssignableFrom(field.getType())){ //문자열 검색
				query.where(entityPath.getString(key).like(("%"+params.get(key)+"%").toLowerCase()));
			/*} else if(Integer.class.isAssignableFrom(field.getType())){ //숫자 검색
				;
			} else if(Date.class.isAssignableFrom(field.getType())){ //날짜 검색
				;*/
			} else {
				query.where(entityPath.get(key).eq(params.get(key)));
			}
		}
		return querydsl.applyPagination(pageable, query).fetch();
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
					where += " AND " + key + "Id IN (SELECT id FROM Code WHERE model='" + modelName + "' AND attribute='" + key + "' AND value IN :" + key +")";
				} else  where += " AND " + key + "Id=:" + key;
			} else if(Set.class.isAssignableFrom(field.getType())) { //Type이 List일 경우
				String keyModel = key.substring(0, 1).toUpperCase() + key.substring(1, key.length()-1);
				String keyId = keyModel + "Id";
				if(field.getAnnotation(ManyToMany.class) != null) {
					String table = null;
					table = ((JoinTable)field.getAnnotation(JoinTable.class)).name();
					where += " AND id in (SELECT " + modelName + "Id FROM " + table + " WHERE " + keyId + "=:" + key + ")";
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
	
	protected JPQLQuery<?> from(EntityPath<?>... paths) {
		return querydsl.createQuery(paths);
	}
}
