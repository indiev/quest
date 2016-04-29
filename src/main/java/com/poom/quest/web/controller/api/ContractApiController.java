package com.poom.quest.web.controller.api;

import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
	
	private static final Logger logger = LoggerFactory.getLogger(ContractApiController.class);
	
	@Autowired UserService userService;
	@Autowired QuestService questService;
	
	@ResponseBody
	@RequestMapping(value = "/agree/{id}", method = RequestMethod.POST)
	public Boolean agree(@PathVariable Integer id, HttpServletRequest request) {
		User user = userService.getLoginUserByRequest(request);
		Contract contract = genericService.get(id);
		Set<User> agreedUsers = contract.getAgreedUsers();
		//이미 추가되어있는지 비교?
		agreedUsers.add(user);
		genericService.update(contract);
		
		Quest quest = contract.getQuest();
		if(quest.getQuesters().size() + 1 == agreedUsers.size()) {
			questService.updateState(quest, "progress");
		}
		
		return true;
	}
}
