package com.poom.quest.web.controller.api;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
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
	
	private static final Logger logger = LoggerFactory.getLogger(QuesterApiController.class);
	
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
	
	@ResponseBody
	@RequestMapping(value = "/{id}/quest/list/{state}", method = RequestMethod.GET)
	public Quester questOfQuester(@PathVariable("id") Integer id, @PathVariable("state") String state, HttpServletRequest request) {
		User user = userService.getLoginUserByRequest(request);
		Quester quester = genericService.get(id);
		quester.getUser();
		Map<String, String> keys = new HashMap<String, String>();
		keys.put("state", state);
		//keys.put(, );
		//keys.put(, );
		questService.getByKeys(keys);
		//검증
		
		return null;
	} 
	
}
