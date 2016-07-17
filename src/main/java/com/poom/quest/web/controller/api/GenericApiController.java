package com.poom.quest.web.controller.api;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.poom.quest.services.model.abstractModel.GenericModel;
import com.poom.quest.services.model.user.User;
import com.poom.quest.services.service.GenericService;
import com.poom.quest.services.service.UserService;
import com.poom.quest.util.reflect.Reflect;

@RequestMapping("api")
public abstract class GenericApiController<T extends GenericModel> {

	@Autowired protected GenericService<T> service;
	@Autowired protected UserService userService;
	protected Class<T> domainClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	
	@ResponseBody
	@RequestMapping
	public List<T> list(@RequestParam Map<String, Object> params) {
		return service.listByKeys(params);
	}

	@ResponseBody
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public T get(@PathVariable Integer id, @RequestParam Map<String, Object> params) {
		return service.get(id);
	}
	
	@ResponseBody
	@RequestMapping(value = "/users/me", method = RequestMethod.GET)
	public List<T> listByMe(@RequestParam Map<String, Object> params) {
		User user = userService.getLoginUserByRequest();
		if(user != null) children(user.getClass().getSimpleName(), user.getId(), params);
		return null;
	}
	
	@ResponseBody
	@RequestMapping(value = "/{parent}s/{parentId}", method = RequestMethod.GET)
	public List<T> children(@PathVariable("parent") String parent, @PathVariable("parentId") Integer parentId, @RequestParam Map<String, Object> params){
		Class<?> parentClass = domainClass;
		if(!parent.equals("parent")) parentClass = Reflect.getField(domainClass, parent).getType();
		return service.listByParent(parentId, parentClass);
	}
	
	@ResponseBody
	@RequestMapping(value = "/{id}/{child}s/{childId}", method = RequestMethod.GET)
	public <S extends GenericModel> S getChildByParent(@PathVariable("id") Integer id, @PathVariable("child") String child, @PathVariable("childId") Integer childId, @RequestParam Map<String, Object> params) {
		try {
			T entity = service.get(id);
			Method method = Reflect.getMethod(domainClass, "get"+child+"s");
			if(method != null)
				for(S childEntity : (Set<S>) method.invoke(entity)) //Child Service를 이용하도록 바꿔야함
					if(childEntity.getId().equals(childId)) return childEntity;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@ResponseBody
	@RequestMapping(value = "/{id}/{child}s", method = RequestMethod.GET)
	public <S extends GenericModel> Set<S> childrenByParent(@PathVariable("id") Integer id, @PathVariable("child") String child, @RequestParam Map<String, Object> params) {
		try {
			T entity = service.get(id);
			Method method = Reflect.getMethod(domainClass, "get"+child+"s");
			if(method != null) return (Set<S>)method.invoke(entity);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@ResponseBody
	@RequestMapping(method = RequestMethod.POST)
	public T add(@RequestBody T entity) {
		//T의 Field Type에 User, Quester, Requester가 있다면. User에서 해당 클래스 넣기
		return service.add(entity);
	}
	
	@ResponseBody
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public T update(@PathVariable Integer id, @RequestParam Map<String, Object> params) {
		int changeCount = 0;
		T entity = service.get(id);
		//T의 Field Type에 User, Quester, Requester가 있다면. 로그인이 되어 있는지 관련 사용자가 맞는지 확인
		for(String key : params.keySet()) {	//바뀌면 안되는 Key 제한. ex) Id, CreatedDate...
			if(key.equalsIgnoreCase("id")) continue;
			try {
				Field field = Reflect.getField(domainClass, key);
				Method method = Reflect.getMethod(domainClass, "set"+field.getName());
				method.invoke(entity, params.get(key));
				changeCount++;
			} catch (Exception e) { 
				continue; 
			}			
		}
		return (changeCount != 0)?service.update(entity):null;
	}
	
	@ResponseBody
	@RequestMapping(value = "/{id}/{child}s/{childId}", method = RequestMethod.PUT)
	public <S> T putChild(@PathVariable("id") Integer id, @PathVariable("child") String child, @PathVariable("childId") Integer childId, @RequestParam Map<String, Object> params) {
		int changeCount = 0;
		T entity = service.get(id);
		//T의 Field Type에 User, Quester, Requester가 있다면. 로그인이 되어 있는지 관련 사용자가 맞는지 확인
		/*try {
			Field field = domainClass.getField(child+"s");
			Method method = domainClass.getMethod("get"+child+"s", domainClass.getClass());
			// find child Service, get ChildEntity
			Class<?> childClass = field.getType();
			S childEntity = (S)childClass.newInstance();
			Set<S> childList = (Set<S>) method.invoke(entity);
			childList.add((S)childEntity);
		} catch (Exception e) { 
			e.printStackTrace(); 
		}*/
		return (changeCount != 0)?service.update(entity):null;
	}
	
	@ResponseBody
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public Integer delete(@PathVariable Integer id, @RequestParam Map<String, Object> params) {
		return service.delete(id);
	}
	
	//보류 uri
	// /{parent}s/{parentId} PUT
	/* 
	 * /{parent}s/{parentId} POST
	@ResponseBody
	@RequestMapping(value = "/{parent}s/{parentId}", method = RequestMethod.POST)
	public T addForParent(@PathVariable("parent") String parent, @PathVariable("parentId") Integer parentId, @RequestBody T entity) {
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
