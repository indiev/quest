package com.poom.quest.services.model.abstractModel;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.poom.quest.services.model.Code;
import com.poom.quest.services.model.user.User;

@MappedSuperclass
public abstract class Log extends Domain {

	private static final long serialVersionUID = 1L;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "actionId", referencedColumnName = "id")
	private Code action;
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "userId", referencedColumnName = "id")
	private User user;

	public Code getAction() {
		return action;
	}

	public void setACtion(Code action) {
		this.action = action;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
