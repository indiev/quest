package com.poom.quest.web.controller.api;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.poom.quest.services.model.user.Quester;
import com.poom.quest.services.model.user.User;
import com.poom.quest.services.service.QuestService;


@Controller
@RequestMapping("api/quester")
public class QuesterApiController extends GenericApiController<Quester> {
	
	@Autowired QuestService questService;
	
	@Override
	@ResponseBody
	@RequestMapping(value = "", method = RequestMethod.POST)
	public Quester add(@RequestBody Quester entity, HttpServletRequest request) {
		User user = userService.getLoginUserByRequest(request);
		if(user != null) {
			entity.setUser(user);
			return genericService.add(entity);
		}
		return null;
	}
}
