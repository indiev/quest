package com.poom.quest.web.controller.api;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.poom.quest.services.domain.Issue;
import com.poom.quest.services.model.user.User;
import com.poom.quest.services.service.IssueService;
import com.poom.quest.web.controller.api.generic.WithUserApiController;

@RestController
@RequestMapping("api/issues")
public class IssueApiController extends WithUserApiController<Issue, Long> {
	@Autowired IssueService issueService;

	@RequestMapping(value = "/{id}/requirements/{requirementId}", method = RequestMethod.PUT)
	public Issue putChild(@PathVariable("id") Long id, @PathVariable("requirementId") Long requirementId, @RequestParam Map<String, Object> params) {
		return null;
	}
	
	@RequestMapping(value = "/approveReport/{issueId}", method = RequestMethod.PUT)
	public Map<String, String> approveReport(@PathVariable("issueId") Long issueId) {
		User user = userService.getLoginUserByRequest();
		if(user != null) return issueService.approveReport(issueId, user);
		else return null;
	}
}