package com.poom.quest.web.controller.api;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.poom.quest.services.model.user.Applicant;
import com.poom.quest.services.model.user.User;
import com.poom.quest.web.controller.api.generic.GenericApiController;


@RestController
@RequestMapping("api/applicants")
public class ApplicantApiController extends GenericApiController<Applicant, Long> {
	
	@Override
	@RequestMapping(value = "/{parent}s/{parentId}", method = RequestMethod.POST)
	public Applicant  add(@PathVariable("parent") String parent, @PathVariable("parentId") Long parentId, @RequestBody Applicant entity) {
		User user = userService.getLoginUserByRequest();
		entity.setQuester(user.getQuester());
		entity.setName(user.getName());
		return super.add(parent, parentId, entity);
	}
}
