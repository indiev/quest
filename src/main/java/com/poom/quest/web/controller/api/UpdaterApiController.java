package com.poom.quest.web.controller.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.poom.quest.services.model.Updater;
import com.poom.quest.services.model.user.User;

@Controller
@RequestMapping("api/updater")
public class UpdaterApiController extends GenericApiController<Updater> {
	
	@Override
	@ResponseBody
	@RequestMapping(value = "", method = RequestMethod.POST)
	public Updater add(@RequestBody Updater entity) {
		User user = userService.getLoginUserByRequest();
		if(user != null) {
			entity.setUser(user);
			return genericService.add(entity);
		}
		return null;
	}
	
	@ResponseBody
	@RequestMapping(value = "/{model}/{attribute}/{refId}", method = RequestMethod.GET)
	public List<Updater> listByRef(@PathVariable("model") String model, @PathVariable("attribute") String attribute, @PathVariable("refId") Integer refId) {
		Map<String, Object> keys = new HashMap<String, Object>();
		keys.put("model", model);
		keys.put("attribute", attribute);
		keys.put("refId", refId);
		return genericService.listByKeys(keys);
	}
}
