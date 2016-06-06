package com.poom.quest.web.controller.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.poom.quest.services.model.Issue;

@Controller
@RequestMapping("/issue")
public class IssueController extends GenericViewController<Issue> {
	
}
