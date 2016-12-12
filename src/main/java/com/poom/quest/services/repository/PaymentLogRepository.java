package com.poom.quest.services.repository;

import org.springframework.stereotype.Repository;

import com.poom.quest.services.domain.log.PaymentLog;

@Repository
public class PaymentLogRepository extends GenericRepository<PaymentLog, Long> {

}
