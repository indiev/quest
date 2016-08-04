package com.poom.quest.web.controller.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.poom.quest.services.model.log.PointLog;
import com.poom.quest.web.controller.api.generic.GenericApiController;

@Controller
@RequestMapping("api/pointLogs")
public class PointLogApiController extends GenericApiController<PointLog, Long> {
	
}
