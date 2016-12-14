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

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.Querydsl;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.poom.quest.services.domain.Code;
import com.poom.quest.services.domain.Model;
import com.poom.quest.services.domain.QCode;
import com.poom.quest.services.domain.abstractModel.Domain;
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
		
		entityPath = new PathBuilder<T>(domainClass, modelName);
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
	public List<T> list(Pageable pageable) {
		return (List<T>) querydsl.applyPagination(pageable, from(entityPath)).fetch();
	}

	public JPQLQuery<T> listByKeys(Map<String, Object> params, JPQLQuery<T> query) {
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
				//String keyModel = key.substring(0, 1).toUpperCase() + key.substring(1, key.length()-1);
				if(field.getAnnotation(ManyToMany.class) != null) {
					String joinTable = ((JoinTable)field.getAnnotation(JoinTable.class)).name();
					Class<T> joinTableClass = (Class<T>) ((ParameterizedType) field.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
					PathBuilder<?> joinTablePath = new PathBuilder<>(joinTableClass, joinTable);
					JPQLQuery<Long> subQuery = from(joinTablePath).select(joinTablePath.getNumber(modelName+"Id", Long.class))
							.where(joinTablePath.get(key+"id").eq(params.get(key)));
					query.where(entityPath.get("id").eq(subQuery));
					//테스트 필요함
				} else { //onetomany
					// join keyModel on model.key = keyModel.key
					//id in (select modelId from table where key=:key)
					//where += " AND id in (SELECT " + model + "Id FROM " + keyModel + " WHERE " + id + "=:key)";
				}
			} else if(String.class.isAssignableFrom(field.getType())){ //문자열 검색
				query.where(entityPath.getString(key).lower().like(("%"+params.get(key)+"%").toLowerCase()));
			/*} else if(Integer.class.isAssignableFrom(field.getType())){ //숫자 검색
				;
			} else if(Date.class.isAssignableFrom(field.getType())){ //날짜 검색
				;*/
			} else {
				query.where(entityPath.get(key).eq(params.get(key)));
			}
		}
		return query;
	}

	public List<T> listByKeys(Map<String, Object> params, Pageable pageable) {
		return querydsl.applyPagination(pageable, listByKeys(params, (JPQLQuery<T>)from(entityPath))).fetch();
	}
	
	public List<T> listByKeys(Map<String, Object> params) {
		return listByKeys(params, (JPQLQuery<T>)from(entityPath)).fetch();
	}
	
	public List<T> listByKeyId(String keyName, ID key) {
		return this.listByKey(keyName + "Id", key);
	}
	
	public List<T> listByKeyId(String keyName, String key) {
		return this.listByKey(keyName + "Id", key);
	}
	
	public List<T> listByKey(String keyName, ID key) {
		JPQLQuery<T> query = (JPQLQuery<T>)from(entityPath);
		if(key != null) query.where(entityPath.get(keyName).eq(key));
		else query.where(entityPath.get(keyName).isNull());
		return query.fetch();
	}
	
	public List<T> listByKey(String keyName, String key) {
		JPQLQuery<T> query = (JPQLQuery<T>)from(entityPath);
		if(key != null) query.where(entityPath.get(keyName).eq(key));
		else query.where(entityPath.get(keyName).isNull());
		return query.fetch();
	}
	
	public List<T> listByParent(ID parentId, Class<?> parentClass) {
		String parentName = parentClass.getSimpleName().toLowerCase();
		if(parentClass.equals(domainClass)) parentName = "parent";
		JPQLQuery<T> query = (JPQLQuery<T>)from(entityPath);
		if(parentId != null) query.where(entityPath.get(parentName).eq(parentId));
		else query.where(entityPath.get(parentName).isNull());
		return query.fetch();
	}
	
	public List<T> listByParent(ID parentId, String parentName) {
		parentName = parentName.toLowerCase();
		JPQLQuery<T> query = (JPQLQuery<T>)from(entityPath);
		if(parentId != null) query.where(entityPath.get(parentName).eq(parentId));
		else query.where(entityPath.get(parentName).isNull());
		return query.fetch();
	}
	
	public List<T> search(String keyword, String[] keys) {
		keyword = ("%" + keyword + "%").toLowerCase();
		JPQLQuery<T> query = (JPQLQuery<T>)from(entityPath);
		if(keys == null || keys.length == 0) keys = new String[]{"name"};
		for (String key : keys) query.where(entityPath.getString(key).lower().like(("%"+keyword.toLowerCase()+"%")));
		return query.fetch();
	}
	
	public List<T> search(String keyword, String[] keys, ID userId) {
		keyword = ("%" + keyword + "%").toLowerCase();
		JPQLQuery<T> query = (JPQLQuery<T>)from(entityPath);
		if(keys == null || keys.length == 0) keys = new String[]{"name"};
		for (String key : keys) query.where(entityPath.getString(key).lower().like(("%"+keyword.toLowerCase()+"%")));
		query.where(entityPath.get("userId").eq(userId));
		return query.fetch();
	}
	
	@Transactional
	public T update(T entity) {
		return em.merge(entity);
	}
	 
	public long count() {
		return from(entityPath).fetchCount();
	}
	
	@Transactional
	public void delete(ID id) {
		em.remove(this.get(id));
	}
	
	protected JPQLQuery<?> from(EntityPath<?>... paths) {
		return querydsl.createQuery(paths);
	}
}
