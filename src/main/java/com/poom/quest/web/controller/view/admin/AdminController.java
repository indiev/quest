package com.poom.quest.web.controller.view.admin;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@RequestMapping(value = "area/edit", method = RequestMethod.GET)
	public String editArea() {
		return "admin/area/edit";
	}
	
	@RequestMapping(value = "work/edit", method = RequestMethod.GET)
	public String editWork() {
		return "admin/work/edit";
	}
	
	@RequestMapping(value = "skill/edit", method = RequestMethod.GET)
	public String editSkill() {
		return "admin/skill/edit";
	}
	
	@RequestMapping(value = "{model}/node/{view}", method = RequestMethod.GET)
	public String node(@PathVariable("model") String model, @PathVariable("view") String view) {
		return "admin/"+ model +"/node/" + view;
	}
}
