package com.poom.quest.web.controller.api;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.poom.quest.services.model.Updater;
import com.poom.quest.services.model.user.User;

@Controller
@RequestMapping("api/updater")
public class UpdaterController extends GenericApiController<Updater> {
	
	@Override
	@ResponseBody
	@RequestMapping(value = "", method = RequestMethod.POST)
	public Updater add(@RequestBody Updater entity, HttpServletRequest request) {
		User user = userService.getLoginUserByRequest(request);
		if(user != null) {
			entity.setUser(user);
			return genericService.add(entity);
		}
		return null;
	}
}
