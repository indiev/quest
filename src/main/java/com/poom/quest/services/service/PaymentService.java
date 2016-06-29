package com.poom.quest.services.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.poom.quest.services.model.PaymentLog;
import com.poom.quest.services.model.user.User;

@Service
@Transactional
public class PaymentService {
	
	public PaymentLog buy(PaymentLog paymentLog, User user) {
		paymentLog.setPrevPoint(user.getPoint());
		/**
		 * 겔져요청전
		 */
		paymentLog.setUser(user);
		paymentLog.setPrevPoint(user.getPoint());

		/**
		 * 결제요청 후 성공.
		 * 
		 */
		return paymentLog;
	}
	
}
