package com.poom.quest.web.controller.view;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.poom.quest.services.service.UserService;

@RequestMapping("/")
public abstract class GenericViewController<T> {

	private Logger logger = Logger.getLogger(this.getClass());
	
//	@Autowired GenericService<T> genericService;
	@Autowired private UserService userService;
	
	@RequestMapping(value = "/template/top", method = RequestMethod.GET)
	public String top(Model model) {
		return "left";
	}
	
	@RequestMapping(value = "/template/bottom", method = RequestMethod.GET)
	public String bottom(Model model) {
		return "left";
	}
	
	@RequestMapping(value = "/template/left", method = RequestMethod.GET)
	public String left(Model model) {
		return "/template/leftSider";
	}
	
	@RequestMapping(value = "/template/right", method = RequestMethod.GET)
	public String right(HttpServletRequest request, Model model) {
		model.addAttribute("user", userService.loginUser(request));
		return "/template/rightSider";
	}
}
