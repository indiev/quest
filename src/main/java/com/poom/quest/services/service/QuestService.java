package com.poom.quest.services.service;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.poom.quest.services.model.Code;
import com.poom.quest.services.model.Contract;
import com.poom.quest.services.model.Provision;
import com.poom.quest.services.model.Quest;
import com.poom.quest.services.model.Requirement;
import com.poom.quest.services.model.Reward;
import com.poom.quest.services.model.user.User;
import com.poom.quest.services.repository.QuestRepository;

@Service
@Transactional
public class QuestService extends GenericService<Quest> {
	
	@Autowired CodeService codeService;
	@Autowired QuestRepository questDao;
	
	public Quest add(Quest entity, User user) {
		entity.setRecruitmentEndDate(new Date());
		entity.setRequester(user.getRequester());
		for(Requirement requirement : entity.getRequirements()) requirement.setQuest(entity);
		for(Reward reward : entity.getRewards()) reward.setQuest(entity);
		entity.setState(codeService.getState("wait"));
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
		Code state = codeService.getState(stateValue);
		 
		if(state != null) {
			if(!quest.getState().getId().equals(state.getId())) {
				quest.setState(state);
				update(quest);
				result.put("success", "상태를 변경했습니다.");
			} else result.put("error", "이미 변경되었습니다.");					
		} else result.put("error", "잘못된 상태 값으로 변경 중입니다.");
		return result;
	}
	
	public List<Quest> questsOfQuester(Integer questerId, String stateValue) {
		Code state = (stateValue != null)?codeService.getState(stateValue):null;
		return questDao.questsOfQuester(questerId, state.getId());
	}
	
	public List<Quest> questsOfApplicant(Integer questerId, String stateValue) {
		Code state = (stateValue != null)?codeService.getState(stateValue):null;
		return questDao.questsOfApplicant(questerId, state.getId());
	}
	
	public List<Quest> searchByState(Integer stateId, String keyword) {
		return questDao.searchByState(stateId, keyword);
	}
}
