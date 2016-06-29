package com.poom.quest.web.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.poom.quest.services.model.Code;
import com.poom.quest.services.model.PaymentLog;
import com.poom.quest.services.model.user.User;
import com.poom.quest.services.service.CodeService;
import com.poom.quest.services.service.PaymentLogService;
import com.poom.quest.services.service.PaymentService;
import com.poom.quest.services.service.UserService;

@Controller
@RequestMapping("api/payment")
public class PaymentApiController {
	@Autowired PaymentService paymentService;
	@Autowired UserService userService;
	@Autowired CodeService codeService;
	@Autowired PaymentLogService paymentLogService;
	
	@ResponseBody
	@RequestMapping(value = "buy", method = RequestMethod.PUT)
	public PaymentLog buy(@RequestBody PaymentLog entity) {
		PaymentLog paymentLog;
		User user = userService.getLoginUserByRequest();
		Code code = codeService.getByKey("value", "buy");
		entity.setType(code);
		if(user !=null ) {
			paymentLog = paymentService.buy(entity, user);
			user.setPoint(user.getPoint() + paymentLog.getRelevantMoney());
			user = userService.update(user);
			paymentLog.setCurrentPoint(user.getPoint());
			paymentLog.setName("결제 성공");
			return paymentLogService.add(paymentLog);
		}
		return null;
		
		
	}
	
	
}
