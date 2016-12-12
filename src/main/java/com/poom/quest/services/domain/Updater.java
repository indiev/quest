package com.poom.quest.services.domain;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.poom.quest.services.domain.abstractModel.WithUserModel;
import com.poom.quest.services.domain.user.User;

@Entity
public class Updater extends WithUserModel {

	private static final long serialVersionUID = 1L;
	
	private String model;
	private String attribute;
	private Integer refId;
	private String alternation;
	private Character type;
	private Character approval;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "userId", referencedColumnName = "id")
	private User user;

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

	public Integer getRefId() {
		return refId;
	}

	public void setRefId(Integer refId) {
		this.refId = refId;
	}

	public String getAlternation() {
		return alternation;
	}

	public void setAlternation(String alternation) {
		this.alternation = alternation;
	}

	public Character getType() {
		return type;
	}

	public void setType(Character type) {
		this.type = type;
	}

	public Character getApproval() {
		return approval;
	}

	public void setApproval(Character approval) {
		this.approval = approval;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
