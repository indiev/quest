package com.poom.quest.services.model.user;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.poom.quest.services.model.Quest;
import com.poom.quest.services.model.abstractModel.GenericModel;

@Entity
public class Requester extends GenericModel {
	
	private static final long serialVersionUID = 1L;

	@JsonIgnore
	@OneToOne(fetch = FetchType.LAZY)
	@Cascade(CascadeType.ALL)
	@JoinColumn(name = "userId", referencedColumnName="id")
	private User user;

	@JsonIgnore
	@OneToMany(mappedBy = "requester", fetch = FetchType.LAZY)
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
