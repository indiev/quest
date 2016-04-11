package com.poom.quest.web.controller.view.admin;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	private static final Logger logger = LoggerFactory.getLogger(AdminController.class);
	
	@RequestMapping(value = "area/add", method = RequestMethod.GET)
	public String main(HttpServletRequest request, Model model) {
		return "admin/area/add";
	}
	

}
