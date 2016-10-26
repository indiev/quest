package com.poom.quest.web.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.poom.quest.services.model.log.PaymentLog;
import com.poom.quest.services.model.user.User;
import com.poom.quest.services.service.PaymentService;
import com.poom.quest.services.service.UserService;

@RestController
@RequestMapping("api/payments")
public class PaymentApiController {
	
	@Autowired PaymentService paymentService;
	@Autowired UserService userService;
	
	@RequestMapping(value = "buy", method = RequestMethod.PUT)
	public PaymentLog buy(@RequestBody PaymentLog entity) {
		User user = userService.getLoginUserByRequest();
		if(user !=null ) return paymentService.buy(entity.getPoint(), user);
		else return null;
	}
}
