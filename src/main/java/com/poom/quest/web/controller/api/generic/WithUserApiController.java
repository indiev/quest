package com.poom.quest.web.controller.api.generic;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.poom.quest.services.model.abstractModel.WithUserModel;
import com.poom.quest.services.model.user.User;
import com.poom.quest.web.controller.api.GenericApiController;

@RequestMapping("api")
public abstract class WithUserApiController<T extends WithUserModel, ID> extends GenericApiController<T, ID> {
	@ResponseBody
	@RequestMapping(value = "/users/me", method = RequestMethod.POST)
	public T addByUser(@RequestBody T entity) {
		User user = userService.getLoginUserByRequest();
		entity.setUser(user);
		if(user != null) return service.add(entity);
		return null;
	}
}
