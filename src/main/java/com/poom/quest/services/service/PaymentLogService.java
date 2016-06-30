package com.poom.quest.services.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.poom.quest.services.model.SampleModel;
import com.poom.quest.services.model.log.PaymentLog;

@Service
@Transactional
public class PaymentLogService extends GenericService<PaymentLog> {
	
}
