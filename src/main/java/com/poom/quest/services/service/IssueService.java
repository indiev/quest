package com.poom.quest.services.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.poom.quest.services.domain.Code;
import com.poom.quest.services.domain.Issue;
import com.poom.quest.services.domain.Quest;
import com.poom.quest.services.domain.Requirement;
import com.poom.quest.services.domain.Reward;
import com.poom.quest.services.domain.user.User;

@Service
@Transactional
public class IssueService extends GenericService<Issue, Long> {

	@Autowired CodeService codeService;
	@Autowired PointService pointService;
	@Autowired QuestService questService;

	public Map<String, String> approveReport(Long issueId, User user) {
		Map<String, String> result = new HashMap<>();
		Issue issue = this.get(issueId);
		if(issue!=null && issue.getQuest().getRequester().equals(user.getRequester()))
			return approveReport(issue, user);
		result.put("error","선택한 보고 이슈가 속한 퀘스트이 리퀘스터가 아닙니다.");
		return result;
	}

	public Map<String, String> approveReport(Issue issue, User user) {
		Map<String, String> result = new HashMap<>();
		Code issueType = issue.getType();
		Code questCompleteState = codeService.getState("complete", Quest.class.getSimpleName());
		Quest quest = issue.getQuest();
		Set<Requirement> issueAllRequirements = issue.getRequirements();
		Set<Requirement> questAllRepuirements = quest.getRequirements();
		Set<Reward> rewards = quest.getRewards();

		if(issue.getClosed().equals(false)) {
			if((issueType.getValue().equals("report"))) {
				/**
				 *  해당 이슈에서 보고 이슈에 걸려있는 요구사항 상태값 true 변경(요구사항 해결).
				 */
				for (Requirement requirement : issueAllRequirements) {
					requirement.setState(true);
				}
				issue.setClosed(true);
				result.put("success", "선택한 보고 이슈를 승인 하였습니다.");
				/**
				 *  만약 퀘스트 요구사항이 모두 승인되었다면 퀘스트의 상태값을 완료로 바꿔주며 포인트를 지급 한다.
				 */
				if(isAllRequirementCompleted(questAllRepuirements)) {
					questService.updateState(quest, questCompleteState.getValue());
					for (Reward reward : rewards) {
						pointService.give(reward);
					}
					result.put("success",result.get("success")+" /퀘스트의 요구사항이 모두 해결되어 퀘스트가 완료되었습니다.(포인트 지급)");
				}
			} else result.put("error", "선택한 이슈가 report 타입이 아닙니다.");
		}else result.put("error","선택한 이슈는 이미 closed 되었습니다.");
		return result;
	}

	private boolean isAllRequirementCompleted(Set<Requirement> requirements) {
		for(Requirement requirement : requirements) {
			if(requirement.getState().equals(false))
				return false;
		}
		return true;
	}

}
