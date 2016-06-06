package com.poom.quest.services.model;

import java.util.Set;

import javax.persistence.Entity;

import com.poom.quest.services.model.abstractModel.GenericModel;

@Entity
public class Issue extends GenericModel {
	private static final long serialVersionUID = 1L;

	private Boolean closed;
	private String description;
	private Code type;
	private Set<Requirement> requirements;
	
	public Boolean getClosed() {
		return closed;
	}
	public void setClosed(Boolean closed) {
		this.closed = closed;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Code getType() {
		return type;
	}
	public void setType(Code type) {
		this.type = type;
	}
	public Set<Requirement> getRequirements() {
		return requirements;
	}
	public void setRequirements(Set<Requirement> requirements) {
		this.requirements = requirements;
	}
}
