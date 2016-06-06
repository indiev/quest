package com.poom.quest.web.controller.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.poom.quest.services.model.Issue;

@Controller
@RequestMapping("api/issue")
public class IssueApiController extends GenericApiController<Issue> {
}
