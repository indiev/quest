package com.poom.quest.web.controller.api;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.poom.quest.services.model.user.User;
import com.poom.quest.services.service.QuesterService;
import com.poom.quest.services.service.UserService;

@Controller
@RequestMapping("api/user")
public class UserApiController extends GenericApiController<User> {
	
	private static final Logger logger = LoggerFactory.getLogger(UserApiController.class);
	
	@Autowired QuesterService questerService;
	
	@Autowired UserService userservice;
	
	@ResponseBody
	@RequestMapping(value = "{id}/mainQuester/{questerId}", method = RequestMethod.PUT)
	public User update(@PathVariable Integer id, @PathVariable Integer questerId) {
		User user = genericService.get(id);
		user.setMainQuester(questerService.get(questerId));
		return genericService.update(user);
	}
	
	
	@ResponseBody
	@RequestMapping(value = "get", method = RequestMethod.GET)
	public User get(HttpServletRequest request) {
		
		User user = userservice.getLoginUserByRequest(request);
		
		return userService.get(user.getId());
	}
	
	

}
