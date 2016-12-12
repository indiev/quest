package com.poom.quest.services.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.poom.quest.services.domain.Code;
import com.poom.quest.services.domain.Contract;
import com.poom.quest.services.domain.Provision;
import com.poom.quest.services.domain.Quest;
import com.poom.quest.services.domain.Requirement;
import com.poom.quest.services.domain.Reward;
import com.poom.quest.services.model.user.User;
import com.poom.quest.services.repository.QuestRepository;

@Service
@Transactional
public class QuestService extends GenericService<Quest, Long> {
	
	@Autowired CodeService codeService;
	@Autowired QuestRepository questRepository;
	
	public Quest add(Quest entity, User user) {
		entity.setRecruitmentEndDate(new Date());
		entity.setRequester(user.getRequester());
		for(Requirement requirement : entity.getRequirements()) requirement.setQuest(entity);
		for(Reward reward : entity.getRewards()) reward.setQuest(entity);
		entity.setState(codeService.getQuestState("wait"));
		Contract contract = new Contract();
		contract.setName("");
		contract.setQuesterPenalty(30);
		contract.setRequesterPenalty(30);
		Set<Provision> provisions = new HashSet<Provision>();  
		for(int i=0; i<3; i++) {
			Provision provision = new Provision();
			provision.setName("조항"+i);
			provisions.add(provision);
			provision.setContract(contract);
		}
		contract.setProvisions(provisions);
		contract.setQuest(entity);
		entity.setContract(contract);
		return add(entity);
	}
	
	public Map<String, String> updateState(Quest quest, String stateValue) {
		Map<String, String> result = new HashMap<>();
		Code state = codeService.getQuestState(stateValue);
		 
		if(state != null) {
			if(!quest.getState().getId().equals(state.getId())) {
				quest.setState(state);
				update(quest);
				result.put("success", "상태를 변경했습니다.");
			} else result.put("error", "이미 변경되었습니다.");					
		} else result.put("error", "잘못된 상태 값으로 변경 중입니다.");
		return result;
	}
	
	public List<Quest> questsOfQuester(Long questerId, String stateValue) {
		Code state = (stateValue != null)?codeService.getQuestState(stateValue):null;
		return questRepository.questsOfQuester(questerId, state.getId());
	}
	
	public List<Quest> questsOfApplicant(Long questerId, String stateValue) {
		Code state = (stateValue != null)?codeService.getQuestState(stateValue):null;
		return questRepository.questsOfApplicant(questerId, state.getId());
	}
	
	public List<Quest> questsOfApplicantOngoing(Long questerId) {
		String[] ongoingStates  = {"wait", "discuss", "progress"};
		List<Long> list = new ArrayList();
		
		for(String stateValue : ongoingStates) {
			list.add(codeService.getQuestState(stateValue).getId());
		}
		return this.questsOfApplicant(questerId, list);
	}
	
	public List<Quest> questsOfApplicant(Long questerId, List<Long> stateIds) {
		return questRepository.questsOfApplicant(questerId, stateIds);
	}
	
	public List<Quest> questsOfRequester(Long requesterId, String stateValue) {
		Code state = (stateValue != null)?codeService.getQuestState(stateValue):null;
		return questRepository.questsOfRequester(requesterId, state.getId());
	}
	
	public List<Quest> questsOfRequesterOngoing(Long requesterId) {
		String[] ongoingStates  = {"wait", "discuss", "progress"};
		List<Long> list = new ArrayList();
		
		for(String stateValue : ongoingStates) {
			list.add(codeService.getQuestState(stateValue).getId());
		}
		return this.questsOfRequester(requesterId, list);
	}
	
	public List<Quest> questsOfRequester(Long requesterId, List<Long> stateIds) {
		return questRepository.questsOfRequester(requesterId, stateIds);
	}
	
	public List<Quest> searchByState(Long stateId, String keyword) {
		return questRepository.searchByState(stateId, keyword);
	}

	

	
}
