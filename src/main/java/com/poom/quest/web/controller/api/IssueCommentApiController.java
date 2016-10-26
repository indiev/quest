package com.poom.quest.web.controller.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.poom.quest.services.model.IssueComment;
import com.poom.quest.web.controller.api.generic.WithUserApiController;

@RestController
@RequestMapping("api/issueComments")
public class IssueCommentApiController extends WithUserApiController<IssueComment, Long> {
}
