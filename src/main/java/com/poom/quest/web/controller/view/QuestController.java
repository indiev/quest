package com.poom.quest.web.controller.view;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.poom.quest.services.model.Code;
import com.poom.quest.services.model.Quest;
import com.poom.quest.services.service.CodeService;

/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping("/quest")
public class QuestController extends GenericViewController<Quest> {
	
	private static final Logger logger = LoggerFactory.getLogger(QuestController.class);
	
	@Autowired CodeService codeService;
	
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
	
	@RequestMapping(value = "detail/{id}", method = RequestMethod.GET)
	public String detail(@PathVariable Integer id, HttpServletRequest request, Model model) {
		Quest quest = genericService.get(id);
		Code state = codeService.get(quest.getState().getId());
		//return return modelName + "/detail/" + state.getName();
		switch (state.getName()) {
		case "wait":
			return modelName + "/detail";
		case "discuss":
			return modelName + "/detail/discuss";
		case "progress":
			return modelName + "/detail/progress";
		case "complete":
			return modelName + "/detail/complete";
		case "stop":
			return modelName + "/detail/stop";
		default:
			return modelName + "/detail";
		}
	}
	
	@RequestMapping(value = "node/list", method = RequestMethod.GET)
	public String listNode(HttpServletRequest request) {
		return modelName + "/node/list";
	}
	
	@RequestMapping(value = "node/detail", method = RequestMethod.GET)
	public String detailNode(HttpServletRequest request) {
		return modelName + "/node/detail";
	}
}
