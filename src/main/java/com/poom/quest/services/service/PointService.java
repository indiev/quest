package com.poom.quest.services.service;

import java.util.Set;

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
		Code code = codeService.getAction("charge", pointLog.getClass().getSimpleName());
		
		user.setPoint(user.getPoint() + point);
		pointLog.setName("action: "+code.getName());
		pointLog.setACtion(code);
		pointLog.setUser(user);
		pointLog.setPoint(point);
		
		return pointLogService.add(pointLog);
	}
	

	public PointLog deposit(Integer rewardId, User user) {
		if(rewardService.get(rewardId) != null && rewardService.get(rewardId).getQuest().getRequester().equals(user.getRequester()))
			return this.deposit(rewardService.get(rewardId));
		return null;
	}
	
	public PointLog deposit(Reward reward) {
		Code requesterActionCode = codeService.getAction("deposit", PointLog.class.getSimpleName());
		Code questerActionCode = codeService.getAction("receiveWait", PointLog.class.getSimpleName());
		Code rewardConditionStateCode = codeService.getState("depositWaiting", Reward.class.getSimpleName());
		Code rewardNextStateCode = codeService.getState("pointKeeping", Reward.class.getSimpleName());
		
		return tradeRequesterToQuester(reward, requesterActionCode, questerActionCode, rewardConditionStateCode, rewardNextStateCode);
	}
	
	public PointLog withdraw(Integer rewardId, User user) {
		if(rewardService.get(rewardId) != null && rewardService.get(rewardId).getQuest().getRequester().equals(user.getRequester()))
			return this.withdraw(rewardService.get(rewardId));
		return null;
	}
	
	private PointLog withdraw(Reward reward) {
		Code requesterActionCode = codeService.getAction("withdraw", PointLog.class.getSimpleName());
		Code questerActionCode = codeService.getAction("depositCancel", PointLog.class.getSimpleName());
		Code rewardConditionStateCode = codeService.getState("pointKeeping", Reward.class.getSimpleName());
		Code rewardNextStateCode = codeService.getState("depositWaiting", Reward.class.getSimpleName());
		
		return tradeRequesterToQuester(reward, requesterActionCode, questerActionCode, rewardConditionStateCode, rewardNextStateCode);
	}


	public PointLog give(Integer rewardId, User user) {
		if(rewardService.get(rewardId) != null && rewardService.get(rewardId).getQuest().getRequester().equals(user.getRequester()))
			return this.give(rewardService.get(rewardId));
		return null;
	}
	
	public PointLog give(Reward reward) {
		Code requesterActionCode = codeService.getAction("give", PointLog.class.getSimpleName());
		Code questerActionCode = codeService.getAction("receive", PointLog.class.getSimpleName());
		Code rewardConditionStateCode = codeService.getState("pointKeeping", Reward.class.getSimpleName());
		Code rewardNextStateCode = codeService.getState("rewardCompleted", Reward.class.getSimpleName());
		
		return tradeRequesterToQuester(reward, requesterActionCode, questerActionCode, rewardConditionStateCode, rewardNextStateCode);
	}
	
	private PointLog tradeRequesterToQuester(Reward reward, Code requesterActionCode, Code questerActionCode, Code rewardConditionStateCode, Code rewardNextStateCode) {
		User requestUser = reward.getQuest().getRequester().getUser();
		Set<Quester> questers = reward.getQuest().getQuesters();
		Integer rewardPointSum = reward.getHwan().intValue();
		Integer rewardPoint = reward.getHwan().intValue()/reward.getQuest().getQuesters().size();
		
		if(!(reward.getState().equals(rewardConditionStateCode)))
			return null;
		
		for (Quester quester: questers) {
			User questUser = quester.getUser();
			if(requesterActionCode.getValue().equals("give"))
				questUser.setPoint(questUser.getPoint()+rewardPoint);
			String logName = "action: "+questerActionCode.getName()+" /questName: "+reward.getQuest().getName()+" /requesterName: "+requestUser.getRequester().getName();
			pointLogService.add(new PointLog(logName, questerActionCode, questUser, rewardPoint, reward ));
		}
		
		if(requesterActionCode.getValue().equals("deposit"))
			requestUser.setPoint(requestUser.getPoint()-rewardPointSum);
		else if(requesterActionCode.getValue().equals("withdraw"))
			requestUser.setPoint(requestUser.getPoint()+rewardPointSum);
		
		reward.setState(rewardNextStateCode);
		String logName = "action: "+requesterActionCode.getName()+" /questName: "+reward.getQuest().getName()+" /requesterName: "+requestUser.getRequester().getName();
		return pointLogService.add(new PointLog(logName, requesterActionCode, requestUser, rewardPointSum, reward));
	}

	
}
