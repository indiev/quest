package com.poom.quest.web.controller.view;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.poom.quest.services.model.user.User;

/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping("/user")
public class UserController extends GenericViewController<User>{ /* 로그인 하지 않은 사용자를 대상으로 한 view 컨트롤러 */
	
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@RequestMapping(value = "login", method = RequestMethod.GET)
	public String login(HttpServletRequest request, Model model) {
		return "user/login";
	}
	
	@RequestMapping(value = "join", method = RequestMethod.GET)
	public String join(HttpServletRequest request, Model model) {
		return "user/join";
	}
	
	/**
	 * 유저 정보 노드.
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "node/detail", method = RequestMethod.GET)
	public String detailNode(HttpServletRequest request, Model model) {
		
		return modelName + "/node/detail";
	}
	
	
	
}
