package com.poom.quest.web.controller.api;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.poom.quest.services.model.Issue;
import com.poom.quest.services.model.user.User;
import com.poom.quest.services.service.IssueService;

@Controller
@RequestMapping("api/issue")
public class IssueApiController extends GenericApiController<Issue> {
	
	@Autowired IssueService issueService;
	
	@Override
	@ResponseBody
	@RequestMapping(value = "", method = RequestMethod.POST)
	public Issue add(@RequestBody Issue entity) {
		User user = userService.getLoginUserByRequest();
		if(user != null) return issueService.add(entity, user);
		else return null;
	}
	
	@ResponseBody
	@RequestMapping(value = "/approveReport/{issueId}", method = RequestMethod.PUT)
	public Map<String, String> approveReport(@PathVariable("issueId") Integer issueId) {
		User user = userService.getLoginUserByRequest();
		if(user != null) return issueService.approveReport(issueId, user);
		else return null;
	}
}
