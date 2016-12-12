package com.poom.quest.web.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.poom.quest.services.domain.Quest;
import com.poom.quest.services.model.user.User;
import com.poom.quest.services.service.QuestService;
import com.poom.quest.services.service.QuesterService;
import com.poom.quest.web.controller.api.generic.GenericApiController;

@RestController
@RequestMapping("api/quests")
public class QuestApiController extends GenericApiController<Quest, Long> {
	
	@Autowired QuestService questService;
	@Autowired QuesterService questerService;

	/*@ResponseBody
	@RequestMapping(value = "all/search", method = RequestMethod.GET)
	public List<Quest> search() {
		return search("");
	}
	
	@ResponseBody
	@RequestMapping(value = "all/search/{keyword}", method = RequestMethod.GET)
	public List<Quest> search(@PathVariable("keyword") String keyword) {
		return service.search(keyword, null);
	}
	
	@ResponseBody
	@RequestMapping(value = "{stateValue}/search", method = RequestMethod.GET)
	public List<Quest> searchBySateValue(@PathVariable String stateValue) {
		return searchBySateValue(stateValue, "");
	}
	
	@ResponseBody
	@RequestMapping(value = "{stateValue}/search/{keyword}", method = RequestMethod.GET)
	public List<Quest> searchBySateValue(@PathVariable("stateValue") String stateValue, @PathVariable("keyword") String keyword) {
		Code state = codeService.getQuestState(stateValue);
		return questService.searchByState(state.getId(), keyword);
	}*/
	
	//정해진 값 범위 내에서 추가되도록, 벗어나는 값 관련 작업 필요
	@Override
	@RequestMapping(method = RequestMethod.POST)
	public Quest add(@RequestBody Quest entity) {
		User user = userService.getLoginUserByRequest();
		if(user != null) return questService.add(entity, user);	//service.add로 수정
		else return null;
	}
	
	/*@ResponseBody
	@RequestMapping(value = "/apply", method = RequestMethod.PUT)
	public Boolean apply(@RequestParam Long id) {
		Quest quest = service.get(id);
		User loginUser = userService.getLoginUserByRequest();
		if(loginUser != null && loginUser != quest.getRequester().getUser()) {
			Set<Quester> applicants = quest.getApplicants();
			applicants.add(loginUser.getQuester());
			service.update(quest);
			return true;
		}
		return false;
	}
	
	@ResponseBody
	@RequestMapping(value = "/accept", method = RequestMethod.PUT)
	public Boolean accept(@RequestParam Long questId, @RequestParam Long questerId) {
		Quest quest = service.get(questId);
		User loginUser = userService.getLoginUserByRequest();
		if(loginUser != null && loginUser == quest.getRequester().getUser()) {
			Quester quester = questerService.get(questerId);
			Set<Quester> applicants = quest.getApplicants();
			applicants.remove(quester);
			Set<Quester> questers = quest.getQuesters();
			questers.add(quester);
			service.update(quest);
			return true;
		}
		return false;
	}*/

	//정해진 상태값 이외의 값으로 변경될 시 에외 처리 추가해야됨
	/*@ResponseBody
	@RequestMapping(value = "/{id}/state/{stateValue}", method = RequestMethod.GET)
	public Map<String, String> updateState(@PathVariable("id") Long id, @PathVariable("stateValue") String stateValue) {
		Map<String, String> result = new HashMap<>();
		User user = userService.getLoginUserByRequest();
		if(user != null) {
			Quest quest = service.get(id);
			if(quest.getRequester().getId().equals(user.getId())) {
				result = questService.updateState(quest, stateValue);
			} else result.put("error", "권한이 없습니다.");
		} else result.put("error", "권한이 없습니다.");
		
		return result;
	}
	
	@ResponseBody
	@RequestMapping(value = "/questerId/{id}/stateGroup/ongoing",  method = RequestMethod.GET)
	public List<Quest> questsOfApplicantOngoing(@PathVariable("id") Long id) {
		User user = userService.getLoginUserByRequest();
		if(user != null) {
			return questService.questsOfApplicantOngoing(id);
		}
		return null;
	}
	
	@ResponseBody
	@RequestMapping(value = "/questerId/{id}/state/{stateValue}",  method = RequestMethod.GET)
	public List<Quest> questsOfApplicant(@PathVariable("id") Long id, @PathVariable("stateValue") String stateValue) {
		User user = userService.getLoginUserByRequest();
		if(user != null) {
			return questService.questsOfApplicant(id, stateValue);
		}
		return null;
	}
	
	@ResponseBody
	@RequestMapping(value = "/requesterId/{id}/stateGroup/ongoing",  method = RequestMethod.GET)
	public List<Quest> questsOfRequesterOngoing(@PathVariable("id") Long id) {
		User user = userService.getLoginUserByRequest();
		if(user != null) {
			return questService.questsOfRequesterOngoing(id);
		}
		return null;
	}
	
	@ResponseBody
	@RequestMapping(value = "/requesterId/{id}/state/{stateValue}",  method = RequestMethod.GET)
	public List<Quest> questsOfRequester(@PathVariable("id") Long id, @PathVariable("stateValue") String stateValue) {
		User user = userService.getLoginUserByRequest();
		if(user != null) {
			return questService.questsOfRequester(id, stateValue);
		}
		return null;
	}*/
}
