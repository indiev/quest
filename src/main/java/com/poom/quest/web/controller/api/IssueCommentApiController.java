package com.poom.quest.web.controller.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.poom.quest.services.model.IssueComment;
import com.poom.quest.web.controller.api.generic.WithUserApiController;

@Controller
@RequestMapping("api/issueComments")
public class IssueCommentApiController extends WithUserApiController<IssueComment, Long> {
}
