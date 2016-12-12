package com.poom.quest.services.domain;

import javax.persistence.Entity;

import com.poom.quest.services.model.abstractModel.Domain;

@Entity
public class Code extends Domain {

	private static final long serialVersionUID = 1L;
	
	private String model;
	private String attribute;
	private String value;
	
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
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
}
