package com.poom.quest.web.controller.api;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.poom.quest.services.model.Code;

@Controller
@RequestMapping("api/code")
public class CodeApiController extends GenericApiController<Code> {
	
	private static final Logger logger = LoggerFactory.getLogger(CodeApiController.class);
	
}
