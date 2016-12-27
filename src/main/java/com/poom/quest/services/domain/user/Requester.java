package com.poom.quest.services.domain.user;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.springframework.data.annotation.CreatedBy;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.poom.quest.services.domain.Quest;
import com.poom.quest.services.domain.abstractModel.WithUserModel;

@Entity
public class Requester extends WithUserModel {
	
	private static final long serialVersionUID = 1L;

	@CreatedBy
	@JsonBackReference
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "userId", referencedColumnName="id")
	private User user;

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
