package com.poom.quest.web.controller.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.poom.quest.services.model.Code;
import com.poom.quest.web.controller.api.generic.GenericApiController;

@Controller
@RequestMapping("api/codes")
public class CodeApiController extends GenericApiController<Code, Long> {
}
