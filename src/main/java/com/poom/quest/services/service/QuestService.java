package com.poom.quest.services.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.poom.quest.services.model.Code;
import com.poom.quest.services.model.Quest;

@Service
@Transactional
public class QuestService extends GenericService<Quest> {
	
	@Autowired CodeService codeService;
	
	public Map<String, String> updateState(Quest quest, String stateValue) {
		Map<String, String> result = new HashMap<>();
		Map<String, String> keys = new HashMap<>();
		keys.put("model", "Quest");
		keys.put("name", stateValue);
		Code state = codeService.getByKeys(keys);
		
		if(state != null) {
			if(!quest.getState().getId().equals(state.getId())) {
				quest.setState(state);
				update(quest);
				result.put("success", "상태를 변경했습니다.");
			} else result.put("error", "이미 변경되었습니다.");					
		} else result.put("error", "잘못된 상태 값으로 변경 중입니다.");
		
		return result;
	}
}
