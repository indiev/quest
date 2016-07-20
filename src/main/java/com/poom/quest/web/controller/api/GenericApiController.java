package com.poom.quest.web.controller.api;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.poom.quest.services.model.Code;
import com.poom.quest.services.model.Model;
import com.poom.quest.services.model.abstractModel.GenericModel;
import com.poom.quest.services.model.user.User;
import com.poom.quest.services.service.CodeService;
import com.poom.quest.services.service.GenericService;
import com.poom.quest.services.service.UserService;
import com.poom.quest.util.reflect.Reflect;

@RequestMapping("api")
public abstract class GenericApiController<T extends GenericModel, ID> {

	@Autowired protected ApplicationContext applicationContext;
	@Autowired protected GenericService<T, ID> service;
	@Autowired protected UserService userService;
	@Autowired protected CodeService codeService;
	protected Class<T> domainClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	
	@ResponseBody
	@RequestMapping
	public List<T> list(@RequestParam Map<String, Object> params) {
		return service.listByKeys(params);
	}

	@ResponseBody
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public T get(@PathVariable ID id, @RequestParam Map<String, Object> params) {
		return service.get(id);
	}
	
	@ResponseBody
	@RequestMapping(value = "/users/me", method = RequestMethod.GET)
	public List<T> listByMe(@RequestParam Map<String, Object> params) {
		User user = userService.getLoginUserByRequest();
		if(user != null) children(user.getClass().getSimpleName(), (ID)user.getId(), params);
		return null;
	}
	
	@ResponseBody
	@RequestMapping(value = "/{parent}s/{parentId}", method = RequestMethod.GET)
	public List<T> children(@PathVariable("parent") String parent, @PathVariable("parentId") ID parentId, @RequestParam Map<String, Object> params){
		Class<?> parentClass = domainClass;
		if(!parent.equals("parent")) parentClass = Reflect.getField(domainClass, parent).getType();
		return service.listByParent(parentId, parentClass);
	}
	
	@ResponseBody
	@RequestMapping(value = "/{id}/{child}s/{childId}", method = RequestMethod.GET)
	public <S extends GenericModel> S getChildByParent(@PathVariable("id") ID id, @PathVariable("child") String child, @PathVariable("childId") ID childId, @RequestParam Map<String, Object> params) {
		try {
			GenericService<S, ID> childService = applicationContext.getBean(child+"Service", GenericService.class);
			T entity = service.get(id);
			Method method = Reflect.getMethod(domainClass, "get"+child+"s");
			if(method != null) {
				Set<S> nodeSet = (Set<S>)method.invoke(entity);
				S childEntity = childService.get(childId);
				if(nodeSet.contains(childEntity)) return childEntity;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@ResponseBody
	@RequestMapping(value = "/{id}/{child}s", method = RequestMethod.GET)
	public <S extends GenericModel> Set<S> childrenByParent(@PathVariable("id") ID id, @PathVariable("child") String child, @RequestParam Map<String, Object> params) {
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
	public T update(@PathVariable ID id, @RequestParam Map<String, Object> params) {
		int changeCount = 0;
		T entity = service.get(id);
		//T의 Field Type에 User, Quester, Requester가 있다면. 로그인이 되어 있는지 관련 사용자가 맞는지 확인
		for(String key : params.keySet()) {	//바뀌면 안되는 Key 제한. ex) Id, CreatedDate...
			if(key.equalsIgnoreCase("id")) continue;
			try {
				Field field = Reflect.getField(domainClass, key);
				if(field != null) {
					Class<?> fieldClass = field.getType();
					Method getMethod = Reflect.getMethod(domainClass, "get"+key);
					Method setMethod = Reflect.getMethod(domainClass, "set"+key);
					if(field.getType().isAssignableFrom(Model.class)) { //Type이 모델이라면
						if(field.getType().isAssignableFrom(Code.class)) {	//Type이 Code일 경우
							Code code = codeService.get(domainClass.getSimpleName(), key, (String)params.get(key));
							setMethod.invoke(entity, code);
						} else {
							GenericService nodeService = applicationContext.getBean(key+"Service", GenericService.class);
							Object node = nodeService.get((ID)params.get(key));
							setMethod.invoke(entity, fieldClass.cast(node));
						}
					} else if(field.getType().isAssignableFrom(Set.class)) { //Type이 List일 경우
						key = key.substring(0, key.length()-1); //'s' 제거
						GenericService nodeService = applicationContext.getBean(key+"Service", GenericService.class);
						Object node = nodeService.get((ID)params.get(key));
						Set nodeSet = (Set)getMethod.invoke(entity);
						nodeSet.add(fieldClass.cast(node));
					} else setMethod.invoke(entity, params.get(key));
					changeCount++;
				}
			} catch (Exception e) { 
				continue; 
			}			
		}
		return (changeCount != 0)?service.update(entity):null;
	}
	
	/*@ResponseBody
	@RequestMapping(value = "/{id}/{child}s", method = RequestMethod.PUT)
	public T putChilds(@PathVariable("id") ID id, @PathVariable("child") String child, @RequestParam Map<String, Object> params) {
		String childIds = (String)params.get("ids");
		if(childIds != null) {
			for(String childId : childIds.split(",")) {
				
			}
		}
		
		return null;
		
	}*/
	
	@ResponseBody
	@RequestMapping(value = "/{id}/{child}s/{childId}", method = RequestMethod.PUT)
	public T putChild(@PathVariable("id") ID id, @PathVariable("child") String child, @PathVariable("childId") ID childId, @RequestParam Map<String, Object> params) {
		int changeCount = 0;
		T entity = service.get(id);
		//T의 Field Type에 User, Quester, Requester가 있다면. 로그인이 되어 있는지 관련 사용자가 맞는지 확인
		try {
			GenericService childService = applicationContext.getBean(child+"Service", GenericService.class);
			Object childEntity = childService.get(childId);
			Method method = Reflect.getMethod(domainClass, "get"+child+"s");
			Set childList = (Set) method.invoke(entity);
			if(childList.contains(childEntity)) childList.remove(childEntity);
			else childList.add(childEntity);
			changeCount++;
		} catch (Exception e) { 
			e.printStackTrace(); 
		}
		return (changeCount != 0)?service.update(entity):null;
	}
	
	@ResponseBody
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ID delete(@PathVariable ID id, @RequestParam Map<String, Object> params) {
		return service.delete(id);
	}
	
	@ResponseBody
	@RequestMapping(value = "/{id}/{child}s/{childId}", method = RequestMethod.DELETE)
	public T removeChild(@PathVariable("id") ID id, @PathVariable("child") String child, @PathVariable("childId") ID childId, @RequestParam Map<String, Object> params) {
		int changeCount = 0;
		T entity = service.get(id);
		//T의 Field Type에 User, Quester, Requester가 있다면. 로그인이 되어 있는지 관련 사용자가 맞는지 확인
		try {
			GenericService childService = applicationContext.getBean(child+"Service", GenericService.class);
			Object childEntity = childService.get(childId);
			Method method = Reflect.getMethod(domainClass, "get"+child+"s");
			Set childList = (Set) method.invoke(entity);
			if(childList.contains(childEntity)) {
				childList.remove(childEntity);
				changeCount++;
			}
		} catch (Exception e) { 
			e.printStackTrace(); 
		}
		return (changeCount != 0)?service.update(entity):null;
	}
	
	//보류 uri
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
