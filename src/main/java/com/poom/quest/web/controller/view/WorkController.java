package com.poom.quest.web.controller.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.poom.quest.services.domain.Work;

@Controller
@RequestMapping("/work")
public class WorkController extends GenericViewController<Work> {
	
}
