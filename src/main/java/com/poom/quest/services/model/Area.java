package com.poom.quest.services.model;

import javax.persistence.Entity;

import com.poom.quest.services.model.abstractModel.TreeModel;

@Entity
public class Area extends TreeModel<Area> {

	private static final long serialVersionUID = 1L;
	
	private String description;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
