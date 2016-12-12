package com.poom.quest.web.controller.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.poom.quest.services.domain.log.PaymentLog;
import com.poom.quest.web.controller.api.generic.GenericApiController;

@RestController
@RequestMapping("api/paymentLogs")
public class PaymentLogApiController extends GenericApiController<PaymentLog, Long> {
	
}
