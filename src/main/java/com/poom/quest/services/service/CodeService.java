package com.poom.quest.services.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.poom.quest.services.domain.Code;
import com.poom.quest.services.domain.abstractModel.Domain;

@Service
@Transactional
public class CodeService extends GenericService<Code, Long> {
	
	public Code get(String model, String attribute, String value) {
		Map<String, String> keys = new HashMap<>();
		keys.put("model", model);
		keys.put("attribute", attribute);
		keys.put("value", value);
		return getByKeys(keys);
	}
	public Code getQuestState(String stateValue) {
		return this.get("Quest", "state", stateValue);
	}
	
	public Code getAction(String value, String model) {
		return this.get(model, "action", value);
	}
	
	public Code getState(String value, String model) {
		return this.get(model, "state", value);
	}
}
