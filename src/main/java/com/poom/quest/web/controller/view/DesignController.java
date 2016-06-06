package com.poom.quest.web.controller.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/design")
public class DesignController {
	
	@RequestMapping(value = "quest/detail", method = RequestMethod.GET)
	public String main() {
		return "design/quest/detail";
	}
	
	@RequestMapping(value = "quest/list", method = RequestMethod.GET)
	public String sample() {
		return "design/quest/list";
	}
}
