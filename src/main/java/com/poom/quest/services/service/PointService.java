package com.poom.quest.services.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.poom.quest.services.model.Code;
import com.poom.quest.services.model.log.PointLog;
import com.poom.quest.services.model.user.User;

@Service
@Transactional
public class PointService {
	
	@Autowired CodeService codeService;
	@Autowired PointLogService pointLogService;
	
	public PointLog charge(Integer point ,User user) {
		PointLog pointLog = new PointLog();
		Code code = codeService.getAction("charge", pointLog);
		pointLog.setPoint(point);
		pointLog.setUser(user);
		pointLog.setACtion(code);
		
		
		user.setPoint(user.getPoint() + point);
		pointLog.setName(code.getName()+"성공");
		
		return pointLogService.add(pointLog);
	}
	
}
