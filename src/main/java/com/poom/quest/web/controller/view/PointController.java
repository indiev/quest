package com.poom.quest.web.controller.view;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.poom.quest.services.domain.user.User;

@Controller
@RequestMapping("/point")
public class PointController {

	@RequestMapping(value = "deposit", method = RequestMethod.GET)
	public String buy() {
		return "point/deposit";
	}
	
	@RequestMapping(value = "withdraw", method = RequestMethod.GET)
	public String withdraw() {
		return "point/withdraw";
	}
	
	@RequestMapping(value = "give", method = RequestMethod.GET)
	public String give() {
		return "point/give";
	}
	
}
