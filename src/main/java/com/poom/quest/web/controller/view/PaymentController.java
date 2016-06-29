package com.poom.quest.web.controller.view;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.poom.quest.services.model.user.User;

@Controller
@RequestMapping("/payment")
public class PaymentController {

	@RequestMapping(value = "buy", method = RequestMethod.GET)
	public String buy(Model model) {
		// TODO Auto-generated method stub
		return "payment/buy";
	}
	
}
