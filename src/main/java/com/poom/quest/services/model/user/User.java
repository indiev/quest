package com.poom.quest.services.model.user;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.poom.quest.services.model.abstractModel.GenericModel;

@Entity
public class User extends GenericModel {
	
	private static final long serialVersionUID = 1L;
	
	//@JsonIgnore
	private String password;
	private String realname;
	private String email;
	private String phone;
	
	@OneToOne(mappedBy = "user")
	@JsonIgnore
	private Requester requester;
	
	@OneToMany(mappedBy = "user")
	@JsonIgnore
	private Set<Quester> questers;

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Requester getRequester() {
		return requester;
	}

	public void setRequester(Requester requester) {
		this.requester = requester;
	}

	public Set<Quester> getQuesters() {
		return questers;
	}

	public void setQuesters(Set<Quester> questers) {
		this.questers = questers;
	}
}
