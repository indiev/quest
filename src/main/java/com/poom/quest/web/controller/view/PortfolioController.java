package com.poom.quest.web.controller.view;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.poom.quest.services.model.Portfolio;

@Controller
@RequestMapping("/portfolio")
public class PortfolioController extends GenericViewController<Portfolio> {
	
	@RequestMapping(value = "add", method = RequestMethod.GET)
	public String add(HttpServletRequest request, Model model) {
		return modelName + "/add";
	}
	
	@RequestMapping(value = "node/detail", method = RequestMethod.GET)
	public String detailNode(HttpServletRequest request, Model model) {
		return modelName + "/node/detail";
	}
	
	@RequestMapping(value = "node/list", method = RequestMethod.GET)
	public String listNode(HttpServletRequest request, Model model) {
		return modelName + "/node/list";
	}
	
	
	
	
	
}
