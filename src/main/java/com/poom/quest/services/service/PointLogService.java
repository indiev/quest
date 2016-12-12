package com.poom.quest.services.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.poom.quest.services.domain.log.PointLog;

@Service
@Transactional
public class PointLogService extends GenericService<PointLog, Long> {
	
}
