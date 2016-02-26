package com.poom.quest.web.controller.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.poom.quest.services.model.Area;

@Controller
@RequestMapping("api/area")
public class AreaApiController extends GenericApiController<Area> {
	
	private static final Logger logger = LoggerFactory.getLogger(AreaApiController.class);
	
}
