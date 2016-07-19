package com.poom.quest.web.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.poom.quest.services.model.user.Quester;
import com.poom.quest.services.model.user.Requester;
import com.poom.quest.services.model.user.User;
import com.poom.quest.services.service.QuesterService;
import com.poom.quest.services.service.RequesterService;

@Controller
@RequestMapping("api/users")
public class UserApiController extends GenericApiController<User, Long> {
	
	@Autowired RequesterService requestserService;
	@Autowired QuesterService questerService;
	
	@Override
	@ResponseBody
	@RequestMapping(value = "", method = RequestMethod.POST)
	public User add(@RequestBody User entity) {
		Requester requester = new Requester();
		Quester quester = new Quester();
		requester.setName(entity.getRealname());
		requester.setUser(entity);
		quester.setName(entity.getRealname());
		quester.setUser(entity);
		entity.setRequester(requester);
		entity.setQuester(quester);
		User user = service.add(entity);
		return user;
	}
	
	@ResponseBody
	@RequestMapping(value = "get", method = RequestMethod.GET)
	public User get() {
		User user = userService.getLoginUserByRequest();
		if(user != null) {
			user = userService.get(user.getId());
			user.setPassword("");
		}
		return user;
	}
}
