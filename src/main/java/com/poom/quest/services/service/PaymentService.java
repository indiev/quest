package com.poom.quest.services.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.poom.quest.services.domain.Code;
import com.poom.quest.services.domain.log.PaymentLog;
import com.poom.quest.services.domain.user.User;

@Service
@Transactional
public class PaymentService {

	@Autowired CodeService codeService;
	@Autowired PaymentLogService paymentLogService;
	@Autowired PointService pointService;

	public PaymentLog buy(Integer point, User user) {
		PaymentLog paymentLog = new PaymentLog();
		Code code = codeService.getAction("buy", paymentLog.getClass().getSimpleName());


		/**
		 * 결제요청전(요청자 설정, 요금 정책 적용.)
		 * 
		 */


		/**
		 * 결제요청 후 성공.(요청자 포인트 증가, paymentLog 기록.)
		 * 
		 */

		if(pointService.charge(point, user) != null) {
			paymentLog.setName(code.getName()+"성공");
			paymentLog.setACtion(code);
			paymentLog.setUser(user);
			paymentLog.setMoney(point * 100);
			paymentLog.setPoint(point);
			return paymentLogService.add(paymentLog);
		}

		return null;
	}
}
