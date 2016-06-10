package com.poom.quest.web.controller.view;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.poom.quest.services.model.user.User;

@Controller
@RequestMapping("/payment")
public class PaymentController extends GenericViewController<User> {

	@Override
	public String main(Model model) {
		// TODO Auto-generated method stub
		return "payment/main";
	}
	
}
