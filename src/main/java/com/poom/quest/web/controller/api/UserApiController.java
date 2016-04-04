package com.poom.quest.web.controller.api;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.poom.quest.services.model.user.Requester;
import com.poom.quest.services.model.user.User;
import com.poom.quest.services.service.RequesterService;

@Controller
@RequestMapping("api/user")
public class UserApiController extends GenericApiController<User> {
	
	private static final Logger logger = LoggerFactory.getLogger(UserApiController.class);
	@Autowired RequesterService requestserService;
	
	@Override
	@ResponseBody
	@RequestMapping(value = "", method = RequestMethod.POST)
	public User add(@RequestBody User entity, HttpServletRequest request) {
		User user = genericService.add(entity);
		Requester requester = new Requester();
		requester.setName(entity.getName());
		requester.setUser(entity);
		requestserService.add(requester);
		return user;
	}
}
