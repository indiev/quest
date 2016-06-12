package com.poom.quest.services.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.poom.quest.services.model.abstractModel.GenericModel;

@Entity
public class Reward extends GenericModel {

	private static final long serialVersionUID = 1L;
	
	private Long hwan;
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "questId", referencedColumnName = "id")
	private Quest quest;

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
}
