package com.poom.quest.services.domain;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.poom.quest.services.domain.abstractModel.Domain;

@Entity
public class Requirement extends Domain {

	private static final long serialVersionUID = 1L;
	
	private String description;
	private Boolean state;
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "questId", referencedColumnName = "id")
	private Quest quest;
	
	@PrePersist
	public void onCreate() {
		if(this.state == null) this.state = false;
		super.onCreate();
	};
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Boolean getState() {
		return state;
	}
	public void setState(Boolean state) {
		this.state = state;
	}
	public Quest getQuest() {
		return quest;
	}
	public void setQuest(Quest quest) {
		this.quest = quest;
	}
}
