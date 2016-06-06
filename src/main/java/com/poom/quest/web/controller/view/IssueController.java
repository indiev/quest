package com.poom.quest.web.controller.view;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.poom.quest.services.model.Issue;

@Controller
@RequestMapping("/issue")
public class IssueController extends GenericViewController<Issue> {
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String main(HttpServletRequest request, Model model) {
		return "sample/main";
	}
	
	@RequestMapping(value = "sample", method = RequestMethod.GET)
	public String sample(HttpServletRequest request, Model model) {
		return "sample/sample";
	}
}
