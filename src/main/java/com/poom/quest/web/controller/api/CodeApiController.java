package com.poom.quest.web.controller.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.poom.quest.services.model.Code;

@Controller
@RequestMapping("api/code")
public class CodeApiController extends GenericApiController<Code> {
	
}
