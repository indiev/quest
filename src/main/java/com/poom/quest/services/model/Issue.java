package com.poom.quest.services.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.poom.quest.services.model.abstractModel.GenericModel;
import com.poom.quest.services.model.user.User;

@Entity
public class Issue extends GenericModel {
	private static final long serialVersionUID = 1L;

	private Boolean closed;
	private String description;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "typeId", referencedColumnName = "id")
	private Code type;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "RequirementIssue", joinColumns = {@JoinColumn(name = "issueId")}, inverseJoinColumns = {@JoinColumn(name = "requirementId")})
	private Set<Requirement> requirements;
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "userId", referencedColumnName = "id")
	private User user;
	
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
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
}
