package com.poom.quest.web.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.poom.quest.services.model.Issue;
import com.poom.quest.services.model.IssueComment;
import com.poom.quest.services.model.user.User;
import com.poom.quest.services.service.IssueService;

@Controller
@RequestMapping("api/issueComment")
public class IssueCommentApiController extends GenericApiController<IssueComment> {
	
	@Autowired IssueService issueService; 
	
	@ResponseBody
	@RequestMapping(value = "/issue/{id}", method = RequestMethod.POST)
	public IssueComment add(@RequestBody IssueComment entity, @PathVariable Integer id) {
		User user = userService.getLoginUserByRequest();
		if(user != null) {
			Issue issue = issueService.get(id);
			entity.setIssue(issue);
			entity.setUser(user);
			return genericService.add(entity);
		}
		return null;
	}
}
