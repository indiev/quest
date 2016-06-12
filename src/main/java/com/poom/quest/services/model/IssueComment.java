package com.poom.quest.services.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.poom.quest.services.model.abstractModel.GenericModel;
import com.poom.quest.services.model.user.User;

@Entity
public class IssueComment extends GenericModel {

	private static final long serialVersionUID = 1L;
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "issueId", referencedColumnName = "id")
	private Issue issue;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@Cascade(CascadeType.MERGE)
	@JoinColumn(name = "userId", referencedColumnName = "id")
	private User user;

	public Issue getIssue() {
		return issue;
	}

	public void setIssue(Issue issue) {
		this.issue = issue;
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
