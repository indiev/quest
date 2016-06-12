package com.poom.quest.web.controller.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.poom.quest.services.model.Code;
import com.poom.quest.services.model.Quest;
import com.poom.quest.services.service.CodeService;

@Controller
@RequestMapping("/quest")
public class QuestController extends GenericViewController<Quest> {
	
	@Autowired CodeService codeService;
	
	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public String detail(@PathVariable Integer id) {
		Quest quest = genericService.get(id);
		Code state = codeService.get(quest.getState().getId());
		//return return modelName + "/detail/" + state.getName();
		switch (state.getValue()) {
		case "wait":
			return modelName + "/detail";
		case "discuss":
			return modelName + "/detail/discuss";
		case "progress":
			return modelName + "/detail/progress";
		case "complete":
			return modelName + "/detail/complete";
		case "stop":
			return modelName + "/detail/stop";
		default:
			return modelName + "/detail";
		}
	}
}
