package com.poom.quest.services.domain.user;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.poom.quest.services.domain.Quest;
import com.poom.quest.services.domain.abstractModel.Domain;

@Entity
public class Applicant extends Domain {

	private static final long serialVersionUID = 1L;

	private Integer hopedReward;
	private String message;
	//private Resume resume; //지원서
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "questerId", referencedColumnName = "id")
	private Quester quester;
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "questId", referencedColumnName = "id")
	private Quest quest;

	public Integer getHopedReward() {
		return hopedReward;
	}

	public void setHopedReward(Integer hopedReward) {
		this.hopedReward = hopedReward;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Quester getQuester() {
		return quester;
	}

	public void setQuester(Quester quester) {
		this.quester = quester;
	}

	public Quest getQuest() {
		return quest;
	}

	public void setQuest(Quest quest) {
		this.quest = quest;
	}
}
