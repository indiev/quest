package com.poom.quest.web.controller.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.poom.quest.services.domain.user.User;

@Controller
@RequestMapping("/user")
public class UserController extends GenericViewController<User>{
	
	@RequestMapping(value = "login", method = RequestMethod.GET)
	public String login() {
		return "user/login";
	}
	
	@RequestMapping(value = "join", method = RequestMethod.GET)
	public String join() {
		return "user/join";
	}
}
