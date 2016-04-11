package com.poom.quest.web.controller.view;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/design")
public class DesignController {
	
	private static final Logger logger = LoggerFactory.getLogger(DesignController.class);
	
	
	@RequestMapping(value = "quest/detail", method = RequestMethod.GET)
	public String main(HttpServletRequest request, Model model) {
		return "design/quest/detail";
	}
	
	@RequestMapping(value = "quest/list", method = RequestMethod.GET)
	public String sample(HttpServletRequest request, Model model) {
		return "design/quest/list";
	}
}
