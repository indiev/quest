package com.poom.quest.services.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.poom.quest.services.dao.GenericDao;
import com.poom.quest.services.model.abstractModel.GenericModel;

@Service
@Transactional
public abstract class GenericService<T> {

	static private final String REGION = "services";
	
	@Autowired GenericDao<T> genericDao;
	
	public T add(T entity) {
		((GenericModel)entity).setCreatedDate(new Date());
		((GenericModel)entity).setModifiedDate(new Date());
		return genericDao.add(entity);
	}
	
	public T get(Integer id) {
		return genericDao.get(id);
	}
	
	public T getByKey(String keyName, String key) {
		return genericDao.getByKey(keyName, key);
	}
	
	public List<T> list() {
		return genericDao.list();
	}
	
	public List<T> listByKey(String keyName, Integer key) {
		return genericDao.listByKey(keyName, key);
	}
	
	public List<T> listByKey(String keyName, String key) {
		return genericDao.listByKey(keyName, key);
	}
	
	public List<T> listByParent(Integer parentId, Class<?> parentClass) {
		return genericDao.listByParent(parentId, parentClass);
	}
	
	public List<T> listByParent(Integer parentId, String parentName) {
		return genericDao.listByParent(parentId, parentName);
	}
	
	public List<T> search(String keyword, String[] keys) {
		return genericDao.search(keyword, keys);
	}
	
	public T update(T entity) {
		((GenericModel)entity).setModifiedDate(new Date());
		return genericDao.update(entity);
	}
	 
	public long count() {
		return genericDao.count();
	}
	
	public Integer delete(Integer id) {
		genericDao.delete(id);
		return id;
	}
}
