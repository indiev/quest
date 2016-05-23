package com.poom.quest.web.controller.api;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.poom.quest.services.model.Contract;
import com.poom.quest.services.model.Quest;
import com.poom.quest.services.model.user.User;
import com.poom.quest.services.service.QuestService;
import com.poom.quest.services.service.UserService;

@Controller
@RequestMapping("api/contract")
public class ContractApiController extends GenericApiController<Contract> {
	
	@Autowired UserService userService;
	@Autowired QuestService questService;
	
	@ResponseBody
	@RequestMapping(value = "/agree/{id}", method = RequestMethod.GET)
	public Boolean checkAgree(@PathVariable Integer id, HttpServletRequest request) {
		User user = userService.getLoginUserByRequest(request);
		Contract contract = genericService.get(id);
		if(contract.getAgreedUsers().contains(user)) return true;
		else return false;
	}
	
	@ResponseBody
	@RequestMapping(value = "/agree/{id}", method = RequestMethod.PUT)
	public Boolean updateAgree(@PathVariable Integer id, HttpServletRequest request) {
		User user = userService.getLoginUserByRequest(request);
		Contract contract = genericService.get(id);
		contract.getAgreedUsers().add(user);
		genericService.update(contract);
		
		Quest quest = contract.getQuest();
		if(quest.getQuesters().size() + 1 == contract.getAgreedUsers().size()) { 	//전부다 동의했다면, 다음단계로.
			questService.updateState(quest, "progress");
		}
		
		return true;
	}
}
