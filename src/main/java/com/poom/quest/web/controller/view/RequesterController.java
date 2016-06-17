package com.poom.quest.web.controller.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.poom.quest.services.model.user.Requester;
import com.poom.quest.services.model.user.User;
import com.poom.quest.services.service.UserService;


@Controller
@RequestMapping("/requester")
public class RequesterController extends GenericViewController<Requester>{
	
	@Autowired UserService userService;
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String main(Model model) {
		User user = userService.getLoginUserByRequest();
		if(user != null) {
			Requester requester = genericService.getByKey("userId", user.getId());
			return super.detail(requester.getId());
		}
		return modelName + "/main";
	}
	
	@RequestMapping(value = "relatedQuests/{id}", method = RequestMethod.GET)
	public String relatedQuests(@PathVariable Integer id) {
		User user = userService.getLoginUserByRequest();
		if(user != null) {
			return modelName + "/relatedQuests";
		}
		return modelName + "/main";
	}
	
	@RequestMapping(value = "relatedQuestsStats/{id}", method = RequestMethod.GET)
	public String relatedQuestsStats(@PathVariable Integer id) {
		User user = userService.getLoginUserByRequest();
		if(user != null) {
			return modelName + "/relatedQuestsStats";
		}
		return modelName + "/main";
	}
	
	@RequestMapping(value = "basicInfo/{id}", method = RequestMethod.GET)
	public String basicInfo(@PathVariable Integer id) {
		User user = userService.getLoginUserByRequest();
		if(user != null) {
			return modelName + "/basicInfo";
		}
		return modelName + "/main";
	}
}
