package com.poom.quest.services.model.user;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.poom.quest.services.model.Quest;
import com.poom.quest.services.model.abstractModel.GenericModel;

@Entity
public class Requester extends GenericModel {
	
	private static final long serialVersionUID = 1L;

	@JsonIgnore
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "userId", referencedColumnName="id")
	private User user;
	
	@OneToMany(mappedBy = "requester", fetch = FetchType.LAZY)
	@JsonIgnore
	private Set<Quest> quests;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Set<Quest> getQuests() {
		return quests;
	}

	public void setQuests(Set<Quest> quests) {
		this.quests = quests;
	}
}
