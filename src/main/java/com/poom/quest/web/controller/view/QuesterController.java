package com.poom.quest.web.controller.view;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.annotations.Source;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.poom.quest.services.model.user.Quester;
import com.poom.quest.services.model.user.User;
import com.poom.quest.services.service.UserService;


@Controller
@RequestMapping("/quester")
public class QuesterController extends GenericViewController<Quester> {
	
	private static final Logger logger = LoggerFactory.getLogger(QuestController.class);
	
	@Autowired UserService userService;
	/**
	 * 새로운 퀘스터 생성 화면 제공
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "add", method = RequestMethod.GET)
	public String add(HttpServletRequest request, Model model) {
		
		User loginedUser = userService.getLoginUserByRequest(request);
		model.addAttribute("loginedUser",loginedUser.getName());
		logger.debug("//////////////"+loginedUser.getName());
		return modelName + "/add";
	}
	
	
	
	
	
}
