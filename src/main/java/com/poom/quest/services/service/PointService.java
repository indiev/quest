package com.poom.quest.services.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.poom.quest.services.model.Code;
import com.poom.quest.services.model.Reward;
import com.poom.quest.services.model.log.PointLog;
import com.poom.quest.services.model.user.Quester;
import com.poom.quest.services.model.user.User;

@Service
@Transactional
public class PointService {
	
	@Autowired CodeService codeService;
	@Autowired PointLogService pointLogService;
	@Autowired RewardService rewardService;
	
	public PointLog charge(Integer point ,User user) {
		PointLog pointLog = new PointLog();
		Code code = codeService.getAction("charge", pointLog);
		
		user.setPoint(user.getPoint() + point);
		pointLog.setName(code.getName()+"성공");
		pointLog.setACtion(code);
		pointLog.setUser(user);
		pointLog.setPoint(point);
		
		return pointLogService.add(pointLog);
	}
	

	public PointLog deposit(Integer rewardId, User requestUser) {
		if(rewardService.get(rewardId) != null)
			return this.deposit(rewardService.get(rewardId), requestUser);
		return null;
	}
	
	public PointLog deposit(Reward reward, User requestUser ) {
		PointLog pointLog = new PointLog();
		Code code = codeService.getAction("deposit", pointLog);
		User questUser;
		
		Integer rewardPointSum = reward.getHwan().intValue();
		Integer rewardPoint = reward.getHwan().intValue()/reward.getQuest().getQuesters().size();
		
		for (Quester quester: reward.getQuest().getQuesters()) {
			questUser = quester.getUser();
//			questUser.setPoint(questUser.getPoint()+rewardPoint);
			pointLog.setName(requestUser.getRequester().getName()+code.getName()+"성공");
			pointLog.setACtion(code);
			pointLog.setUser(questUser);
			pointLog.setPoint(rewardPoint);
			pointLog.setReward(reward);
			pointLogService.add(pointLog);
		}
		
		requestUser.setPoint(requestUser.getPoint()-rewardPointSum);
		pointLog.setName(code.getName()+"성공");
		pointLog.setACtion(code);
		pointLog.setUser(requestUser);
		pointLog.setPoint(rewardPointSum);
		pointLog.setReward(reward);
		
		
		return pointLogService.add(pointLog);
	}

	
}
