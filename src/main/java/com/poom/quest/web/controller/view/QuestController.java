package com.poom.quest.web.controller.view;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.poom.quest.services.model.Quest;

/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping("/quest")
public class QuestController extends GenericViewController<Quest> {
	
	private static final Logger logger = LoggerFactory.getLogger(QuestController.class);
	
	@RequestMapping(value = "mainlist", method = RequestMethod.GET)
	public String mainlist(HttpServletRequest request, Model model) {
		return modelName + "/mainlist";
	}
	
	@RequestMapping(value = "apply", method = RequestMethod.GET)
	public String apply(HttpServletRequest request, Model model) {
		return modelName + "/apply";
	}
	
	@RequestMapping(value = "complete", method = RequestMethod.GET)
	public String complete(HttpServletRequest request, Model model) {
		return modelName + "/complete";
	}
	
	@RequestMapping(value = "ing", method = RequestMethod.GET)
	public String ing(HttpServletRequest request, Model model) {
		return modelName + "/ing";
	}
	
	@RequestMapping(value = "ok", method = RequestMethod.GET)
	public String ok(HttpServletRequest request, Model model) {
		return modelName + "/ok";
	}
	
	@RequestMapping(value = "request_form", method = RequestMethod.GET)
	public String request_form(HttpServletRequest request, Model model) {
		return modelName + "/request_form";
	}
	
	@RequestMapping(value = "request", method = RequestMethod.GET)
	public String request(HttpServletRequest request, Model model) {
		return modelName + "/request";
	}
	
	@RequestMapping(value = "reward", method = RequestMethod.GET)
	public String reward(HttpServletRequest request, Model model) {
		return modelName + "/reward";
	}
	
	@RequestMapping(value = "node/list", method = RequestMethod.GET)
	public String listNode(HttpServletRequest request, Model model) {
		return modelName + "/node/list";
	}
	
	@RequestMapping(value = "node/detail", method = RequestMethod.GET)
	public String detailNode(HttpServletRequest request, Model model, @PathVariable Integer id) {
		return modelName + "/node/detail";
	}
}
