package com.poom.quest.web.controller.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.poom.quest.services.model.IssueComment;

@Controller
@RequestMapping("api/issueComment")
public class IssueCommentApiController extends GenericApiController<IssueComment> {
}
