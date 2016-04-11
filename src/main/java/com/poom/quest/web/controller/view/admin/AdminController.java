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
	/**
	 * area(분야) 추가 화면.
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "area/add", method = RequestMethod.GET)
	public String addArea(HttpServletRequest request, Model model) {
		return "admin/area/add";
	}
	
	/**
	 * work(업무) 추가 화면. 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "work/add", method = RequestMethod.GET)
	public String addWork(HttpServletRequest request, Model model) {
		return "admin/work/add";
	}
	
	/**
	 * skill(기술) 추가 화면.
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "skill/add", method = RequestMethod.GET)
	public String addSkill(HttpServletRequest request, Model model) {
		return "admin/skill/add";
	}
	
	

}
