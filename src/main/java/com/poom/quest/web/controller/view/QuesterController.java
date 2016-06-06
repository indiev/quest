package com.poom.quest.web.controller.view;

import javax.servlet.http.HttpServletRequest;

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
	
	@Autowired UserService userService;

	@RequestMapping(value = "", method = RequestMethod.GET)
	public String main(HttpServletRequest request, Model model) {
		User user = userService.getLoginUserByRequest(request);
		if(user != null) {
			Quester quester = genericService.getByKey("userId", user.getId());
			return super.detail(quester.getId(), request);
		}
		return modelName + "/main";
	}
	
	@RequestMapping(value = "add", method = RequestMethod.GET)
	public String add(HttpServletRequest request, Model model) {
		return modelName + "/add";
	}
	
}
