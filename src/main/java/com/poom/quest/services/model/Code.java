package com.poom.quest.services.model;

import javax.persistence.Entity;

import com.poom.quest.services.model.abstractModel.GenericModel;

@Entity
public class Code extends GenericModel {

	private static final long serialVersionUID = 1L;
	
	private String model;
	private String attribute;
	private String description;
	
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getAttribute() {
		return attribute;
	}
	public void setAttribute(String attribute) {
		this.attribute = attribute;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}
