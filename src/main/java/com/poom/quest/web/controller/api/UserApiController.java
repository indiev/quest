package com.poom.quest.web.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.poom.quest.services.model.user.Quester;
import com.poom.quest.services.model.user.Requester;
import com.poom.quest.services.model.user.User;
import com.poom.quest.services.service.QuesterService;
import com.poom.quest.services.service.RequesterService;
import com.poom.quest.web.controller.api.generic.GenericApiController;

@RestController
@RequestMapping("api/users")
public class UserApiController extends GenericApiController<User, Long> {
	
	@Autowired RequesterService requestserService;
	@Autowired QuesterService questerService;
	
	@Override
	@RequestMapping(method = RequestMethod.POST)
	public User add(@RequestBody User entity) {
		Requester requester = new Requester();
		Quester quester = new Quester();
		requester.setName(entity.getRealname());
		requester.setUser(entity);
		quester.setName(entity.getRealname());
		quester.setUser(entity);
		entity.setRequester(requester);
		entity.setQuester(quester);
		User user = getService().add(entity);
		return user;
	}
	
	@RequestMapping(value = "/me", method = RequestMethod.GET)
	public User get() {
		User user = userService.getLoginUserByRequest();
		if(user != null) {
			user = userService.get(user.getId());
			user.setPassword("");
		}
		return user;
	}
}
