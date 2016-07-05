package com.poom.quest.web.controller.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.poom.quest.services.model.log.PaymentLog;

@Controller
@RequestMapping("/paymentLog")
public class PaymentLogController extends GenericViewController<PaymentLog> {
	
}
