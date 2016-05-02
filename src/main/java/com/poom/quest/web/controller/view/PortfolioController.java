package com.poom.quest.web.controller.view;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.poom.quest.services.model.Portfolio;

/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping("/portfolio")
public class PortfolioController extends GenericViewController<Portfolio> {
	
	private static final Logger logger = LoggerFactory.getLogger(PortfolioController.class);
	
	/**
	 * 새로운 포트폴리오 생성 화면 제공.
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "add", method = RequestMethod.GET)
	public String add(HttpServletRequest request, Model model) {
		return modelName + "/add";
	}
	
	/**
	 * 포트폴리오 정보 노드.
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "node/detail", method = RequestMethod.GET)
	public String detailNode(HttpServletRequest request, Model model) {
		return modelName + "/node/detail";
	}
	
	
	
	
}
