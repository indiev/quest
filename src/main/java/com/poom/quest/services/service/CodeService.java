package com.poom.quest.services.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.poom.quest.services.model.Code;;

@Service
@Transactional
public class CodeService extends GenericService<Code> {
	
	public Code getState(String stateValue) {
		Map<String, String> keys = new HashMap<>();
		keys.put("model", "Quest");
		keys.put("name", stateValue);
		return getByKeys(keys);
	}
}
