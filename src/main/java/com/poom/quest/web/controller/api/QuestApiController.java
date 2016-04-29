package com.poom.quest.web.controller.api;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.poom.quest.services.model.Code;
import com.poom.quest.services.model.Contract;
import com.poom.quest.services.model.Quest;
import com.poom.quest.services.model.Requirement;
import com.poom.quest.services.model.user.Quester;
import com.poom.quest.services.model.user.User;
import com.poom.quest.services.service.CodeService;
import com.poom.quest.services.service.ContractService;
import com.poom.quest.services.service.QuestService;
import com.poom.quest.services.service.QuesterService;
import com.poom.quest.services.service.UserService;

@Controller
@RequestMapping("api/quest")
public class QuestApiController extends GenericApiController<Quest> {
	
	private static final Logger logger = LoggerFactory.getLogger(QuestApiController.class);
	
	@Autowired QuestService questService;
	@Autowired UserService userService;
	@Autowired QuesterService questerService;
	@Autowired CodeService codeService;
	@Autowired ContractService contractService;
	
	//정해진 값 범위 내에서 추가되도록, 벗어나는 값 관련 작업 필요
	@Override
	@ResponseBody
	@RequestMapping(value = "", method = RequestMethod.POST)
	public Quest add(@RequestBody Quest entity, HttpServletRequest request) {
		User user = userService.getLoginUserByRequest(request);
		if(user != null) {
			entity.setRequester(user.getRequester());
			for(Requirement requirement : entity.getRequirements()) requirement.setQuest(entity);
			entity.setState(codeService.getState("wait"));
			Contract contract = new Contract();
			contract.setQuest(entity);
			contractService.add(contract);
			entity.setContract(contract);
			genericService.add(entity);
		}
		return entity;
	}
	
	@ResponseBody
	@RequestMapping(value = "/apply", method = RequestMethod.POST)
	public Boolean apply(@RequestParam Integer id, HttpServletRequest request) {
		Quest quest = genericService.get(id);
		User loginUser = userService.getLoginUserByRequest(request);
		if(loginUser != null && loginUser != quest.getRequester().getUser() && loginUser.getMainQuester() != null) {
			Set<Quester> applicants = quest.getApplicants();
			applicants.add(loginUser.getMainQuester());
			genericService.update(quest);
			return true;
		}
		return false;
	}
	
	@ResponseBody
	@RequestMapping(value = "/accept", method = RequestMethod.POST)
	public Boolean accept(@RequestParam Integer questId, @RequestParam Integer questerId, HttpServletRequest request) {
		Quest quest = genericService.get(questId);
		User loginUser = userService.getLoginUserByRequest(request);
		if(loginUser != null && loginUser == quest.getRequester().getUser()) {
			Quester quester = questerService.get(questerId);
			Set<Quester> applicants = quest.getApplicants();
			applicants.remove(quester);
			Set<Quester> questers = quest.getQuesters();
			questers.add(quester);
			genericService.update(quest);
			return true;
		}
		return false;
	}

	//정해진 상태값 이외의 값으로 변경될 시 에외 처리 추가해야됨
	@ResponseBody
	@RequestMapping(value = "/{id}/state/{stateValue}", method = RequestMethod.GET)
	public Map<String, String> updateState(@PathVariable("id") Integer id, @PathVariable("stateValue") String stateValue, HttpServletRequest request) {
		Map<String, String> result = new HashMap<>();
		User user = userService.getLoginUserByRequest(request);
		if(user != null) {
			Quest quest = genericService.get(id);
			if(quest.getRequester().getId().equals(user.getId())) {
				result = questService.updateState(quest, stateValue);
			} else result.put("error", "권한이 없습니다.");
		} else result.put("error", "권한이 없습니다.");
		
		return result;
	}
}
