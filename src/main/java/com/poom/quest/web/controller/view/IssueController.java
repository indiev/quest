package com.poom.quest.web.controller.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.poom.quest.services.model.Issue;

@Controller
@RequestMapping("/issue")
public class IssueController extends GenericViewController<Issue> {

	@RequestMapping(value = "approveReport", method = RequestMethod.GET)
	public String approveReport() {
		return modelName + "/approveReport";
	}
}
