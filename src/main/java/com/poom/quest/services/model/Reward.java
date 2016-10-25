package com.poom.quest.services.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.poom.quest.services.model.abstractModel.Domain;

@Entity
public class Reward extends Domain {

	private static final long serialVersionUID = 1L;
	
	private Long hwan;
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "questId", referencedColumnName = "id")
	private Quest quest;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "stateId", referencedColumnName = "id")
	private Code state; //상태 - 예치대기(waiting), 예치중(keeping), 보상완료(completed)

	public Long getHwan() {
		return hwan;
	}

	public void setHwan(Long hwan) {
		this.hwan = hwan;
	}
	
	public Quest getQuest() {
		return quest;
	}

	public void setQuest(Quest quest) {
		this.quest = quest;
	}

	public Code getState() {
		return state;
	}

	public void setState(Code state) {
		this.state = state;
	}
	
	
}
