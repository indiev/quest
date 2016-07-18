package com.poom.quest.web.controller.api;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.poom.quest.services.model.Code;
import com.poom.quest.services.model.Issue;
import com.poom.quest.services.model.user.User;
import com.poom.quest.services.service.CodeService;
import com.poom.quest.services.service.IssueService;
import com.poom.quest.web.controller.api.generic.WithUserApiController;

@Controller
@RequestMapping("api/issues")
public class IssueApiController extends WithUserApiController<Issue> {
	@Autowired IssueService issueService;
	@Autowired CodeService codeService;

	@ResponseBody
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public Issue update(@PathVariable Integer id, @RequestParam Map<String, Object> params) {
		String type = (String) params.get("type");
		Boolean closed = (Boolean) params.get("closed");
		Issue issue = service.get(id);
		issue.getType().getName();
		if(type != null) {
			if(type.equals(issue.getType().getName())) {
				if(type.equals("report")) {
					if(closed != null) {
						User user = userService.getLoginUserByRequest();
						user.getRequester();
					}
				}
			} else {
				Code code = codeService.get(Issue.class.getSimpleName(), "type", type);
				issue.setType(code);
			}
		}
		return null;
	}
	
	@ResponseBody
	@RequestMapping(value = "/approveReport/{issueId}", method = RequestMethod.PUT)
	public Map<String, String> approveReport(@PathVariable("issueId") Integer issueId) {
		User user = userService.getLoginUserByRequest();
		if(user != null) return issueService.approveReport(issueId, user);
		else return null;
	}
}
