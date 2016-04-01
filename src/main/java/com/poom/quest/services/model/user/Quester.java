package com.poom.quest.services.model.user;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.poom.quest.services.model.Quest;
import com.poom.quest.services.model.abstractModel.GenericModel;

@Entity
public class Quester extends GenericModel {

	private static final long serialVersionUID = 1L;

	@ManyToOne
	@JoinColumn(name = "userId", referencedColumnName="id")
	private User user;
	
	@ManyToMany(mappedBy = "questers", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonIgnore
	private Set<Quest> quests;

	@ManyToMany(mappedBy = "applicants", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonIgnore
	private Set<Quest> appliedQuests;
	
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

	public Set<Quest> getAppliedQuests() {
		return appliedQuests;
	}

	public void setAppliedQuests(Set<Quest> appliedQuests) {
		this.appliedQuests = appliedQuests;
	}
}
