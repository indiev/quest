package com.poom.quest.services.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.poom.quest.services.model.Code;
import com.poom.quest.services.model.abstractModel.GenericModel;

@Service
@Transactional
public class CodeService extends GenericService<Code> {
	
	public Code getState(String stateValue) {
		Map<String, String> keys = new HashMap<>();
		keys.put("model", "Quest");
		keys.put("attribute", "state");
		keys.put("value", stateValue);
		return getByKeys(keys);
	}
	
	public Code getAction(String actionValue, GenericModel model) {
		Map<String, String> keys = new HashMap<>();
		keys.put("model", model.getClass().getSimpleName());
		keys.put("attribute", "action");
		keys.put("value", actionValue);
		return getByKeys(keys);
	}
}
