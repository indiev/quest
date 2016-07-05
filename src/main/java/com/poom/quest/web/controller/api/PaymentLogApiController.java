package com.poom.quest.web.controller.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.poom.quest.services.model.log.PaymentLog;

@Controller
@RequestMapping("api/paymentLog")
public class PaymentLogApiController extends GenericApiController<PaymentLog> {
	
}
