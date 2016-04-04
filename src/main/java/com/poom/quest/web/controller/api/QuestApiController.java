package com.poom.quest.web.controller.api;

import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.poom.quest.services.model.Quest;
import com.poom.quest.services.model.user.Quester;
import com.poom.quest.services.model.user.User;
import com.poom.quest.services.service.QuesterService;
import com.poom.quest.services.service.UserService;

@Controller
@RequestMapping("api/quest")
public class QuestApiController extends GenericApiController<Quest> {
	
	private static final Logger logger = LoggerFactory.getLogger(QuestApiController.class);
	
	@Autowired UserService userService;
	@Autowired QuesterService questerService;
	
	@Override
	@ResponseBody
	@RequestMapping(value = "", method = RequestMethod.POST)
	public Quest add(@RequestBody Quest entity, HttpServletRequest request) {
		Quest quest = null;
		User user = userService.getLoginUserByRequest(request);
		if(user != null) {
			entity.setRequester(user.getRequester());
			quest = genericService.add(entity);
		}
		
		return quest;
	}
	
	@ResponseBody
	@RequestMapping(value = "/apply", method = RequestMethod.POST)
	public Boolean apply(@RequestParam Integer id, HttpServletRequest request) {
		Quest quest = genericService.get(id);
		User loginUser = userService.getLoginUserByRequest(request);
		if(loginUser != null && loginUser != quest.getRequester().getUser() && loginUser.getMainQuester() != null) {
			Set<Quester> applicants = quest.getApplicants();
			applicants.add(loginUser.getMainQuester());
			genericService.update(quest);
			return true;
		}
		return false;
	}
	
	@ResponseBody
	@RequestMapping(value = "/accept", method = RequestMethod.POST)
	public Boolean accept(@RequestParam Integer questId, @RequestParam Integer questerId, HttpServletRequest request) {
		Quest quest = genericService.get(questId);
		User loginUser = userService.getLoginUserByRequest(request);
		if(loginUser != null && loginUser == quest.getRequester().getUser()) {
			Quester quester = questerService.get(questerId);
			Set<Quester> applicants = quest.getApplicants();
			applicants.remove(quester);
			Set<Quester> questers = quest.getQuesters();
			questers.add(quester);
			genericService.update(quest);
			return true;
		}
		return false;
	}
}
