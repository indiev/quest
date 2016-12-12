package com.poom.quest.web.controller.view;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.poom.quest.services.domain.user.User;

@Controller
@RequestMapping("/payment")
public class PaymentController {

	@RequestMapping(value = "buy", method = RequestMethod.GET)
	public String buy() {
		return "payment/buy";
	}
	
}
