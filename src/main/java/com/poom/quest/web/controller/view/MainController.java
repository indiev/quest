package com.poom.quest.web.controller.view;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class MainController { /* 기본 메인 화면에 대한 view 컨트롤러 */
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String main(HttpServletRequest request, Model model) {
		model.addAttribute("mainContent", "main");
		return "main";
	}
	
	@RequestMapping(value = "/main", method = RequestMethod.GET)
	public String mainContent() {
		return "mainContent";
	}
	
	@RequestMapping(value = "/header", method = RequestMethod.GET)
	public String sidebarTop() {
		return "common/header";
	}
	
	@RequestMapping(value = "/footer", method = RequestMethod.GET)
	public String footoer() {
		return "common/footer";
	}
}
