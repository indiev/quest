package com.poom.quest.web.controller.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.poom.quest.services.model.log.PointLog;
import com.poom.quest.web.controller.api.generic.GenericApiController;

@RestController
@RequestMapping("api/pointLogs")
public class PointLogApiController extends GenericApiController<PointLog, Long> {
	
}
