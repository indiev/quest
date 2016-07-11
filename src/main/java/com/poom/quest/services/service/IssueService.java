package com.poom.quest.services.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.poom.quest.services.model.Code;
import com.poom.quest.services.model.Issue;
import com.poom.quest.services.model.Quest;
import com.poom.quest.services.model.Requirement;
import com.poom.quest.services.model.Reward;
import com.poom.quest.services.model.user.User;

@Service
@Transactional
public class IssueService extends GenericService<Issue> {
	
	@Autowired CodeService codeService;
	@Autowired PointService pointService;
	@Autowired QuestService questService;
	
	public Issue add(Issue entity, User user) {
		entity.setUser(user);
		return add(entity);
	}
	

	public Map<String, String> approveReport(Integer issueId, User user) {
		Map<String, String> result = new HashMap<>();
		Issue issue = this.get(issueId);
		if(issue!=null && issue.getQuest().getRequester().equals(user.getRequester()))
			return approveReport(issue, user);
		else
			return null;
	}
	
	public Map<String, String> approveReport(Issue issue, User user) {
		Map<String, String> result = new HashMap<>();
		Code issueType = issue.getType();
		Code questCompleteState = codeService.getState("complete", Quest.class.getSimpleName());
		Quest quest = issue.getQuest();
		Set<Requirement> issueAllRequirements = issue.getRequirements();
		Set<Requirement> questAllRepuirements = quest.getRequirements();
		Set<Reward> rewards = quest.getRewards();
		
		
		if((issueType.getValue().equals("report"))) {
			/**
			 *  해당 이슈에서 보고한 보고서 상태값 true 변경.
			 */
			for (Requirement requirement : issueAllRequirements) {
				requirement.setState(true);
			}
			issue.setClosed(true);
		} else {
			result.put("error", "해당 이슈가 report 타입이 아닙니다.");
		}
			
		
		
		/**
		 *  만약 퀘스트 요구사항 보고 이슈가 승인되었다면 퀘스트의 상태값을 완료로 바꿔주며 포인트를 지급 한다.
		 */
		for(Requirement requirement : questAllRepuirements) {
			if(requirement.getState().equals(false))
				return null;
		}
		questService.updateState(quest, questCompleteState.getValue()).containsKey("success");
		
		for (Reward reward : rewards) {
			pointService.give(reward);
		}
		
		return null;
	}

	
}
