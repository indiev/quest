package com.poom.quest.services.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.poom.quest.services.repository.GenericRepository;

@Service
@Transactional
public abstract class GenericService<T, ID> {

	static private final String REGION = "services";
	
	@Autowired GenericRepository<T, ID> genericRepository;
	
	public T add(T entity) {
		return genericRepository.add(entity);
	}
	
	public T get(ID id) {
		return genericRepository.get(id);
	}
	
	public T getByKey(String keyName, String key) {
		return genericRepository.getByKey(keyName, key);
	}
	
	public T getByKey(String keyName, ID key) {
		return genericRepository.getByKey(keyName, key.toString());
	}
	
	public T getByKeys(Map<String, String> keys) {
		return genericRepository.getByKeys(keys);
	}
	
	public List<T> list() {
		return genericRepository.list();
	}
	
	public List<T> listByKeyId(String keyName, ID key) {
		return genericRepository.listByKeyId(keyName, key);
	}
	
	public List<T> listByKeyId(String keyName, String key) {
		return genericRepository.listByKeyId(keyName, key);
	}
	
	public List<T> listByKey(String keyName, ID key) {
		return genericRepository.listByKey(keyName, key);
	}
	
	public List<T> listByKey(String keyName, String key) {
		return genericRepository.listByKey(keyName, key);
	}
	
	public List<T> listByParent(ID parentId, Class<?> parentClass) {
		return genericRepository.listByParent(parentId, parentClass);
	}
	
	public List<T> listByParent(ID parentId, String parentName) {
		return genericRepository.listByParent(parentId, parentName);
	}
	
	public List<T> listByKeys(Map<String, Object> keys) {
		return genericRepository.listByKeys(keys);
	}
	
	public List<T> search(String keyword, String[] keys) {
		return genericRepository.search(keyword, keys);
	}
	
	public List<T> search(String keyword, String[] keys, ID userId) {
		return genericRepository.search(keyword, keys, userId);
	}
	
	public T update(T entity) {
		return genericRepository.update(entity);
	}
	 
	public long count() {
		return genericRepository.count();
	}
	
	public ID delete(ID id) {
		genericRepository.delete(id);
		return id;
	}
}
