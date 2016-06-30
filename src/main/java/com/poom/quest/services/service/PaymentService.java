package com.poom.quest.services.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.poom.quest.services.model.Code;
import com.poom.quest.services.model.log.PaymentLog;
import com.poom.quest.services.model.user.User;

@Service
@Transactional
public class PaymentService {

	@Autowired CodeService codeService;
	@Autowired PaymentLogService paymentLogService;
	
	public PaymentLog buy(PaymentLog paymentLog, User user) {
		Code code = codeService.getAction("buy");
		paymentLog.setACtion(code);
		
		/**
		 * 결제요청전(요청자 설정, 요금 정책 적용.)
		 * 
		 */
		paymentLog.setUser(user);
		paymentLog.setMoney(paymentLog.getPoint() * 100);

		/**
		 * 결제요청 후 성공.(요청자 포인트 증가, paymentLog 기록.)
		 * 
		 */
		
		user.setPoint(user.getPoint()+paymentLog.getPoint());
		paymentLog.setName("모의 결제 성공");
		paymentLogService.add(paymentLog);
		return paymentLog;
	}
}
