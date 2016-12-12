package com.poom.quest.services.domain;

import javax.persistence.Entity;

import com.poom.quest.services.model.abstractModel.Domain;

@Entity
public class Skill extends Domain {

	private static final long serialVersionUID = 1L;
	
	private String description;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
