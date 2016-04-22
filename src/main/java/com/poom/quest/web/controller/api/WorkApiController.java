package com.poom.quest.web.controller.api;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.poom.quest.services.model.Work;

@Controller
@RequestMapping("api/work")
public class WorkApiController extends GenericApiController<Work> {
	
	private static final Logger logger = LoggerFactory.getLogger(WorkApiController.class);
	
	@ResponseBody
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public List<Work> list() {
		return genericService.listByKeyId("parent", (Integer)null);
	}
}
