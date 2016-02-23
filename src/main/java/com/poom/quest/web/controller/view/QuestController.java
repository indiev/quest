package com.poom.quest.web.controller.view;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping("/quest")
public class QuestController {
	
	private static final Logger logger = LoggerFactory.getLogger(QuestController.class);
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String main(HttpServletRequest request, Model model) {
		return "quest/main";
	}
	
	@RequestMapping(value = "list", method = RequestMethod.GET)
	public String list(HttpServletRequest request, Model model) {
		return "quest/list";
	}
	
	@RequestMapping(value = "mainlist", method = RequestMethod.GET)
	public String mainlist(HttpServletRequest request, Model model) {
		return "quest/mainlist";
	}
	
	@RequestMapping(value = "apply", method = RequestMethod.GET)
	public String apply(HttpServletRequest request, Model model) {
		return "quest/apply";
	}
	
	@RequestMapping(value = "complete", method = RequestMethod.GET)
	public String complete(HttpServletRequest request, Model model) {
		return "quest/complete";
	}
	
	@RequestMapping(value = "ing", method = RequestMethod.GET)
	public String ing(HttpServletRequest request, Model model) {
		return "quest/ing";
	}
	
	@RequestMapping(value = "ok", method = RequestMethod.GET)
	public String ok(HttpServletRequest request, Model model) {
		return "quest/ok";
	}
	
	@RequestMapping(value = "request_form", method = RequestMethod.GET)
	public String request_form(HttpServletRequest request, Model model) {
		return "quest/request_form";
	}
	
	@RequestMapping(value = "request", method = RequestMethod.GET)
	public String request(HttpServletRequest request, Model model) {
		return "quest/request";
	}
	
	@RequestMapping(value = "reward", method = RequestMethod.GET)
	public String reward(HttpServletRequest request, Model model) {
		return "quest/reward";
	}
	
}
