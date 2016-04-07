package com.poom.quest.web.controller.api;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.poom.quest.services.model.user.Quester;
import com.poom.quest.services.model.user.User;


@Controller
@RequestMapping("api/quester")
public class QuesterApiController extends GenericApiController<Quester> {
	
	private static final Logger logger = LoggerFactory.getLogger(QuesterApiController.class);
	
	@Override
	@ResponseBody
	@RequestMapping(value = "", method = RequestMethod.POST)
	public Quester add(@RequestBody Quester entity, HttpServletRequest request) {
		User user = userService.getLoginUserByRequest(request);
		if(user != null) {
			entity.setUser(user);
			return genericService.add(entity);
		}
		return null;
	}
	
	
	
}