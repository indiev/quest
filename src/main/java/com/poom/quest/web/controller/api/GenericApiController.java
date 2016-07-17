package com.poom.quest.web.controller.api;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

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

@RequestMapping("api")
public abstract class GenericApiController<T extends GenericModel> {

	@Autowired GenericService<T> service;
	@Autowired UserService userService;
	Class<T> domainClass;
	
	@ResponseBody
	@RequestMapping
	public List<T> list(@RequestParam Map<String, Object> params) {
		return service.list();
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
		if(user != null) listByParent(user.getClass().getSimpleName(), user.getId(), params);
		return null;
	}
	
	@ResponseBody
	@RequestMapping(value = "/{parent}s/{parentId}", method = RequestMethod.GET)
	public List<T> listByParent(@PathVariable("parent") String parent, @PathVariable("parentId") Integer parentId, @RequestParam Map<String, Object> params){
		try {
			Class<?> parentClass = domainClass.getField(parent).getType();
			return service.listByParent(parentId, parent);
		} catch (NoSuchFieldException | SecurityException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	//temp
	@ResponseBody
	@RequestMapping(value = "/{idForParent}/{parent}s/{parentId}", method = RequestMethod.GET)
	public T getByParent(@PathVariable("idForParent") Integer idForParent, @PathVariable("parent") String parent, @PathVariable("parentId") Integer parentId, @RequestParam Map<String, Object> params) {
		try {
			Class<?> parentClass = domainClass.getField(parent).getType();
			return service.get(idForParent);
		} catch (NoSuchFieldException | SecurityException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@ResponseBody
	@RequestMapping(method = RequestMethod.POST)
	public T add(@RequestBody T entity) {
		return service.add(entity);
	}
	
	@ResponseBody
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public T update(@PathVariable Integer id, @RequestParam Map<String, Object> params) {
		int changeCount = 0;
		T entity = service.get(id);
		for(String key : params.keySet()) {
			try {
				Field field = domainClass.getField(key);
				Method method = domainClass.getMethod("set"+field.getName(), domainClass.getClass());
				method.invoke(entity, params.get(key));
				changeCount++;
			} catch (Exception e) { 
				continue; 
			}			
		}
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
