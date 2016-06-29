package com.poom.quest.services.model.user;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.poom.quest.services.model.Portfolio;
import com.poom.quest.services.model.abstractModel.GenericModel;

@Entity
public class User extends GenericModel {
	
	private static final long serialVersionUID = 1L;
	
	private String password;
	private String realname;
	private String email;
	private String phone;
	private int point;
	
	@OneToOne(mappedBy = "user", fetch = FetchType.LAZY)
	@Cascade(CascadeType.ALL)
	private Requester requester;
	
	@OneToOne(mappedBy = "user", fetch = FetchType.LAZY)
	@Cascade(CascadeType.ALL)
	private Quester quester;
	
	@JsonIgnore
	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
	@Cascade(CascadeType.ALL)
	private Set<Portfolio> portfolios;

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
	
	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
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

	public Quester getQuester() {
		return quester;
	}

	public void setQuester(Quester quester) {
		this.quester = quester;
	}

	public Set<Portfolio> getPortfolios() {
		return portfolios;
	}

	public void setPortfolios(Set<Portfolio> portfolios) {
		this.portfolios = portfolios;
	}
}
