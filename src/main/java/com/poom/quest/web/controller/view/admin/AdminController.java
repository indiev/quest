package com.poom.quest.web.controller.view.admin;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@RequestMapping(value = "area/add", method = RequestMethod.GET)
	public String addArea(HttpServletRequest request, Model model) {
		return "admin/area/add";
	}
	
	@RequestMapping(value = "work/add", method = RequestMethod.GET)
	public String addWork(HttpServletRequest request, Model model) {
		return "admin/work/add";
	}
	
	@RequestMapping(value = "skill/add", method = RequestMethod.GET)
	public String addSkill(HttpServletRequest request, Model model) {
		return "admin/skill/add";
	}
}
