package com.poom.quest.services.model.log;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.poom.quest.services.model.Code;
import com.poom.quest.services.model.Reward;
import com.poom.quest.services.model.abstractModel.Log;
import com.poom.quest.services.model.user.User;

@Entity
public class PointLog extends Log {

	private static final long serialVersionUID = 1L;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "rewardId", referencedColumnName = "id")
	private Reward reward;

	private Integer point;
	
	public PointLog() {}
	
	public PointLog(String name, Code action, User user, Integer point, Reward reward) {
		this.setName(name);
		this.setACtion(action);
		this.setUser(user);
		this.setPoint(point);
		this.setReward(reward);
		
	}
	
	
	public Reward getReward() {
		return reward;
	}

	public void setReward(Reward reward) {
		this.reward = reward;
	}

	public Integer getPoint() {
		return point;
	}

	public void setPoint(Integer point) {
		this.point = point;
	}
	
	
}
