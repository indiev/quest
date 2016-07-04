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
		PointLog pointLog = new PointLog();
		Code depositCode = codeService.getAction("deposit", pointLog.getClass().getSimpleName());
		Code receiveWaitCode= codeService.getAction("receiveWait", pointLog.getClass().getSimpleName());
		Code rewardCode = codeService.getState("rewardKeeping", reward.getClass().getSimpleName());
		User requestUser = reward.getQuest().getRequester().getUser();
		User questUser;
		
		Integer rewardPointSum = reward.getHwan().intValue();
		Integer rewardPoint = reward.getHwan().intValue()/reward.getQuest().getQuesters().size();
		
		if(!(reward.getState().equals(codeService.getState("rewardWaiting",reward.getClass().getSimpleName()))))
			return null;
		
		for (Quester quester: reward.getQuest().getQuesters()) {
			questUser = quester.getUser();
//			questUser.setPoint(questUser.getPoint()+rewardPoint);
			pointLog.setName("action: "+receiveWaitCode.getName()+" /questName: "+reward.getQuest().getName()+" /requesterName: "+requestUser.getRequester().getName());
			pointLog.setACtion(receiveWaitCode);
			pointLog.setUser(questUser);
			pointLog.setPoint(rewardPoint);
			pointLog.setReward(reward);
			pointLogService.add(pointLog);
		}
		
		requestUser.setPoint(requestUser.getPoint()-rewardPointSum);
		pointLog.setName("action: "+depositCode.getName()+" /questName: "+reward.getQuest().getName()+" /requesterName: "+requestUser.getRequester().getName());
		pointLog.setACtion(depositCode);
		pointLog.setUser(requestUser);
		pointLog.setPoint(rewardPointSum);
		pointLog.setReward(reward);
		
		reward.setState(rewardCode);
		return pointLogService.add(pointLog);
	}
	
	public PointLog give(Integer rewardId, User user) {
		if(rewardService.get(rewardId) != null && rewardService.get(rewardId).getQuest().getRequester().equals(user.getRequester()))
			return this.give(rewardService.get(rewardId));
		return null;
	}
	
	public PointLog give(Reward reward) {
		PointLog pointLog = new PointLog();
		Code giveCode = codeService.getAction("give", pointLog.getClass().getSimpleName());
		Code receiveCode = codeService.getAction("receive", pointLog.getClass().getSimpleName());
		Code rewardCode = codeService.getState("rewardCompleted", reward.getClass().getSimpleName());
		User requestUser = reward.getQuest().getRequester().getUser();
		User questUser;
		
		Integer rewardPointSum = reward.getHwan().intValue();
		Integer rewardPoint = reward.getHwan().intValue()/reward.getQuest().getQuesters().size();
		
		if(!(reward.getState().equals(codeService.getState("rewardKeeping",reward.getClass().getSimpleName()))))
			return null;
		
		for (Quester quester: reward.getQuest().getQuesters()) {
			questUser = quester.getUser();
			questUser.setPoint(questUser.getPoint()+rewardPoint);
			pointLog.setName("action: "+receiveCode.getName()+" /questName: "+reward.getQuest().getName()+" /requesterName: "+requestUser.getRequester().getName());
			pointLog.setACtion(receiveCode);
			pointLog.setUser(questUser);
			pointLog.setPoint(rewardPoint);
			pointLog.setReward(reward);
			pointLogService.add(pointLog);
		}
		
		pointLog.setName("action: "+giveCode.getName()+" /questName: "+reward.getQuest().getName()+" /requesterName: "+requestUser.getRequester().getName());
		pointLog.setACtion(giveCode);
		pointLog.setUser(requestUser);
		pointLog.setPoint(rewardPointSum);
		pointLog.setReward(reward);
		
		reward.setState(rewardCode);
		return pointLogService.add(pointLog);
	}
	

	
}
