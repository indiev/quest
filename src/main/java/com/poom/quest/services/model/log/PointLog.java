package com.poom.quest.services.model.log;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.poom.quest.services.model.Reward;
import com.poom.quest.services.model.abstractModel.Log;

@Entity
public class PointLog extends Log {

	private static final long serialVersionUID = 1L;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "rewardId", referencedColumnName = "id")
	private Reward reward;
	
}
