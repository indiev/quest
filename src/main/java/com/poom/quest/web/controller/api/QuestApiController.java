package com.poom.quest.web.controller.api;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.poom.quest.services.model.Quest;
import com.poom.quest.services.model.user.User;
import com.poom.quest.services.service.UserService;

@Controller
@RequestMapping("api/quest")
public class QuestApiController extends GenericApiController<Quest> {
	
	private static final Logger logger = LoggerFactory.getLogger(QuestApiController.class);
	
	@Autowired UserService userService;
	
	@Override
	@ResponseBody
	@RequestMapping(value = "", method = RequestMethod.POST)
	public Quest add(@RequestBody Quest entity, HttpServletRequest request) {
		HttpSession session = request.getSession(true);
		SecurityContext securityContext = (SecurityContext) session.getAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY);
		String name = securityContext.getAuthentication().getName();
		User user = userService.getByKey("name", name);
		entity.setRequester(user.getRequester());
		return genericService.add(entity);
	}
}
