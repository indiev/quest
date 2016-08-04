package com.poom.quest.web.controller.api.generic;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.poom.quest.services.model.abstractModel.WithUserModel;
import com.poom.quest.services.model.user.User;
import com.poom.quest.services.service.GenericService;
import com.poom.quest.util.reflect.Reflect;

@RequestMapping("api")
public abstract class WithUserApiController<T extends WithUserModel, ID> extends GenericApiController<T, ID> {
	@ResponseBody
	@RequestMapping(method = RequestMethod.POST)
	public T add(@RequestBody T entity) {
		User user = userService.getLoginUserByRequest();
		if(user != null) {
			entity.setUser(user);
			return super.add(entity);
		}
		return null;
	}
	
	@ResponseBody
	@RequestMapping(value = "/{parent}s/{parentId}", method = RequestMethod.POST)
	public T add(@PathVariable("parent") String parent, @PathVariable("parentId") ID parentId, @RequestBody T entity) {
		try {
			User user = userService.getLoginUserByRequest();
			entity.setUser(user);
			if(user != null) {
				GenericService<?, ID> parentServcie = getFieldService(parent);
				Object parentEntity = parentServcie.get(parentId);
				Method method = Reflect.getMethod(domainClass, parent);
				method.invoke(entity, parentEntity);
				entity.setUser(user);
				return super.add(entity);
			}
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
		}
		return null;
	}
}
