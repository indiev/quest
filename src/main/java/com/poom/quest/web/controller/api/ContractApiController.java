package com.poom.quest.web.controller.api;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.poom.quest.services.model.Contract;
import com.poom.quest.services.model.Quest;
import com.poom.quest.services.model.user.User;
import com.poom.quest.services.service.QuestService;
import com.poom.quest.services.service.UserService;

@Controller
@RequestMapping("api/contracts")
public class ContractApiController extends GenericApiController<Contract> {
	
	@Autowired UserService userService;
	@Autowired QuestService questService;
	
	@ResponseBody
	@RequestMapping(value = "/{id}/AgreeUsers/me", method = RequestMethod.GET)
	public User getChildByParent(@PathVariable("id") Integer id, @RequestParam Map<String, Object> params) {
		User user = userService.getLoginUserByRequest();
		Contract contract = service.get(id);
		if(contract.getAgreedUsers().contains(user)) return user;
		return null;
	}
	
	@ResponseBody
	@RequestMapping(value = "/{id}/AgreeUsers/me", method = RequestMethod.PUT)
	public Contract putChild(@PathVariable("id") Integer id, @RequestParam Map<String, Object> params) {
		User user = userService.getLoginUserByRequest();
		Contract contract = service.get(id);
		contract.getAgreedUsers().add(user);
		service.update(contract);
		
		Quest quest = contract.getQuest();
		if(quest.getQuesters().size() + 1 <= contract.getAgreedUsers().size()) { 	//전부다 동의했다면, 다음단계로.
			questService.updateState(quest, "progress");
		}
		
		return contract;
	}
}
