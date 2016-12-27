package com.poom.quest.web.controller.api.generic;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.poom.quest.services.domain.Code;
import com.poom.quest.services.domain.Model;
import com.poom.quest.services.domain.abstractModel.Domain;
import com.poom.quest.services.domain.abstractModel.TreeModel;
import com.poom.quest.services.domain.user.User;
import com.poom.quest.services.service.CodeService;
import com.poom.quest.services.service.GenericService;
import com.poom.quest.services.service.UserService;
import com.poom.quest.util.reflect.Reflect;

@RequestMapping("api")
public abstract class GenericApiController<T extends Domain, ID extends Serializable> {

	@Autowired protected ApplicationContext applicationContext;
	@Autowired protected UserService userService;
	@Autowired protected CodeService codeService;
	@Autowired protected GenericService<T, ID> service;
	protected Class<T> domainClass;
	
	
	protected GenericApiController() {
		domainClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}
	
	@RequestMapping
	public List<T> list(@RequestParam Map<String, Object> params, @PageableDefault(sort = {"createdDate"}, direction = Direction.DESC, size = 6) Pageable pageable) {
		return getService().listByKeys(params, pageable);
	}
	
	/*@RequestMapping
	public List<T> list(@RequestParam Map<String, Object> params) {
		return getService().listByKeys(params);
	}*/

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public T get(@PathVariable ID id, @RequestParam Map<String, Object> params) {
		return getService().get(id);
	}
	
	@RequestMapping(value = "/users/me", method = RequestMethod.GET)
	public List<T> listByMe(@RequestParam Map<String, Object> params) {
		User user = userService.getLoginUserByRequest();
		return (user != null)?children(user.getClass().getSimpleName(), (ID)user.getId(), params):null;
	}
	
	@RequestMapping(value = "/{parent}s/{parentId}", method = RequestMethod.GET)
	public List<T> children(@PathVariable("parent") String parent, @PathVariable("parentId") ID parentId, @RequestParam Map<String, Object> params){
		Field field = Reflect.getField(domainClass, parent);
		if(field != null) {
			params.put(parent, parentId);
			return getService().listByKeys(params);
		} else {
			field = Reflect.getField(domainClass, parent + "s"); //manyToMany
			if(field != null) {
				params.put(parent+"s", parentId);
				return getService().listByKeys(params);
			} else return null;
		}
	}
	
	@RequestMapping(value = "/{id}/{child}s/{childId}", method = RequestMethod.GET)
	public <S extends Domain> S getChildByParent(@PathVariable("id") ID id, @PathVariable("child") String child, @PathVariable("childId") ID childId, @RequestParam Map<String, Object> params) {
		try {
			T entity = getService().get(id);
			Method method = Reflect.getMethod(domainClass, "get"+child+"s");
			if(method != null) {
				Set<S> nodeSet = (Set<S>)method.invoke(entity);
				GenericService childService = getService(child);
				S childEntity = (S) childService.get(childId);
				if(nodeSet.contains(childEntity)) return childEntity;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping(value = "/{id}/{child}s", method = RequestMethod.GET)
	public <S extends Domain> Set<S> childrenByParent(@PathVariable("id") ID id, @PathVariable("child") String child, @RequestParam Map<String, Object> params) {
		/*Field field = Reflect.getField(domainClass, child + "s");
		if(field == null) return null;
		GenericService<S, Long> childService = (GenericService<S, Long>) getService(child);
		System.out.println(childService);
		if(childService != null) {
			params.put(domainClass.getSimpleName(), id);
			return (List<S>) childService.listByKeys(params);			
		} else return null;*/
		try {
			T entity = getService().get(id);
			Method method = Reflect.getMethod(domainClass, "get"+child+"s");
			if(method != null) {
				return (Set<S>)method.invoke(entity);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public T add(@RequestBody T entity) {
		//T의 Field Type에 User, Quester, Requester가 있다면. User에서 해당 클래스 넣기
		return getService().add(entity);
	}
	
	@RequestMapping(value = "/{parent}s/{parentId}", method = RequestMethod.POST)
	public T add(@PathVariable("parent") String parent, @PathVariable("parentId") ID parentId, @RequestBody T entity) {
		//T의 Field Type에 User, Quester, Requester가 있다면. User에서 해당 클래스 넣기
		Field parentField = Reflect.getField(domainClass, parent);
		if(parentField == null) return null;
		Object parentEntity = null;
		if(TreeModel.class.isAssignableFrom(domainClass) && parent.equals("parent")) { //Tree Model
			parentEntity = getService().get(parentId);
		} else {
			GenericService<?, ID> parentService = this.getFieldService(parent);
			parentEntity = parentService.get(parentId);
		}
		try {
			Method setMethod = Reflect.getMethod(domainClass, "set" + parent);
			setMethod.invoke(entity, parentEntity);
			return getService().add(entity);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public T update(@PathVariable ID id, @RequestParam Map<String, Object> params) {
		int changeCount = 0;
		T entity = getService().get(id);
		//T의 Field Type에 User, Quester, Requester가 있다면. 로그인이 되어 있는지 관련 사용자가 맞는지 확인
		for(String key : params.keySet()) {	//바뀌면 안되는 Key 제한. ex) Id, CreatedDate...
			if(key.equalsIgnoreCase("id")) continue;
			try {
				Field field = Reflect.getField(domainClass, key);
				if(field != null) {
					Class<?> fieldClass = field.getType();
					Method getMethod = Reflect.getMethod(domainClass, "get"+key);
					Method setMethod = Reflect.getMethod(domainClass, "set"+key);
					if(Model.class.isAssignableFrom(field.getType())) { //Type이 모델이라면
						if(Code.class.isAssignableFrom(field.getType())) {	//Type이 Code일 경우
							Code code = codeService.get(domainClass.getSimpleName(), key, (String)params.get(key));
							setMethod.invoke(entity, code);
						} else {
							GenericService<?, ID> nodeService = getFieldService(key);
							Object node = nodeService.get((ID)params.get(key));
							setMethod.invoke(entity, fieldClass.cast(node));
						}
					} else if(Set.class.isAssignableFrom(field.getType())) { //Type이 List일 경우
						key = key.substring(0, key.length()-1); //'s' 제거
						GenericService<?, ID> nodeService = getFieldService(key);
						Object node = nodeService.get((ID)params.get(key));
						Set nodeSet = (Set)getMethod.invoke(entity);
						nodeSet.add(fieldClass.cast(node));
					} else setMethod.invoke(entity, params.get(key));
					changeCount++;
				}
			} catch (Exception e) { 
			  e.printStackTrace();
			}			
		}
		return (changeCount != 0)?getService().update(entity):null;
	}
	
	/*
	@RequestMapping(value = "/{id}/{child}s", method = RequestMethod.PUT)
	public T putChilds(@PathVariable("id") ID id, @PathVariable("child") String child, @RequestParam Map<String, Object> params) {
		String childIds = (String)params.get("ids");
		if(childIds != null) {
			for(String childId : childIds.split(",")) {
				
			}
		}
		
		return null;
		
	}*/
	
	@RequestMapping(value = "/{id}/{child}s/{childId}", method = RequestMethod.PUT)
	public T putChild(@PathVariable("id") ID id, @PathVariable("child") String child, @PathVariable("childId") ID childId, @RequestParam Map<String, Object> params) {
		int changeCount = 0;
		T entity = getService().get(id);
		//T의 Field Type에 User, Quester, Requester가 있다면. 로그인이 되어 있는지 관련 사용자가 맞는지 확인
		try {
			GenericService<?, ID> childService = getFieldService(child);
			Object childEntity = childService.get(childId);
			Method method = Reflect.getMethod(domainClass, "get"+child+"s");
			Set<Object> childList = (Set<Object>) method.invoke(entity);
			if(childList.contains(childEntity)) childList.remove(childEntity);
			else childList.add(childEntity);
			changeCount++;
		} catch (Exception e) { 
			e.printStackTrace(); 
		}
		return (changeCount != 0)?getService().update(entity):null;
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ID delete(@PathVariable ID id, @RequestParam Map<String, Object> params) {
		return getService().delete(id);
	}
	
	@RequestMapping(value = "/{id}/{child}s/{childId}", method = RequestMethod.DELETE)
	public T removeChild(@PathVariable("id") ID id, @PathVariable("child") String child, @PathVariable("childId") ID childId, @RequestParam Map<String, Object> params) {
		int changeCount = 0;
		T entity = getService().get(id);
		//T의 Field Type에 User, Quester, Requester가 있다면. 로그인이 되어 있는지 관련 사용자가 맞는지 확인
		try {
			GenericService<?, ID> childService = getFieldService(child);
			Object childEntity = childService.get(childId);
			Method method = Reflect.getMethod(domainClass, "get"+child+"s");
			Set<?> childList = (Set<?>) method.invoke(entity);
			if(childList.contains(childEntity)) {
				childList.remove(childEntity);
				changeCount++;
			}
		} catch (Exception e) { 
			e.printStackTrace(); 
		}
		return (changeCount != 0)?getService().update(entity):null;
	}
	
	private GenericService<?, ID> getService(String model) {
		return applicationContext.getBean(model+"Service", GenericService.class);			
	}
	
	public GenericService<?, ID> getFieldService(String fieldName) {
		return getService(fieldName);
	}

	public GenericService<T, ID> getService() {
		return service;
	}

	public void setService(GenericService<T, ID> service) {
		this.service = service;
	}
	
	//보류 ;ri
	// /{parent}s/{parentId} PUT
	/* 
	 * /{parent}s/{parentId} POST
	@ResponseBody
	@RequestMapping(value = "/{parent}s/{parentId}", method = RequestMethod.POST)
	public T addForParent(@PathVariable("parent") String parent, @PathVariable("parentId") ID parentId, @RequestBody T entity) {
		try {
			Class<?> parentClass = domainClass.getField(parent).getType();
			Method methodOfSetParent = domainClass.getMethod("set"+parent, domainClass.getClass());
			//find parentService
			//Object parentObject = parentService.get(parentId); - method Invoke
			//methodOfSetParent.invoke(entity, parentObject);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return service.add(entity);
	}
	*/
}
