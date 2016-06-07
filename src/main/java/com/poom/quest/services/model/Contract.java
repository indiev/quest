package com.poom.quest.services.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.poom.quest.services.model.abstractModel.GenericModel;
import com.poom.quest.services.model.user.User;

@Entity
public class Contract extends GenericModel {

	private static final long serialVersionUID = 1L;
	
	private Integer requesterPenalty; //리퀘스터에 대한 위약금 수치. 전체 금액의 %
	private Integer questerPenalty; //퀘스터에 대항 위약금 수치. 전체 금액의 % 
	
	@OneToMany(mappedBy = "contract")
	private Set<Provision> provisions; //계약조항 디폴트 값. 폼, 템플릿 제공
	
	@JsonIgnore
	@OneToOne(fetch = FetchType.LAZY)
	@Cascade(CascadeType.ALL)
	@JoinColumn(name = "questId", referencedColumnName="id")
	private Quest quest;	//관련 퀘스트
	
	@ManyToMany(fetch = FetchType.LAZY)
	@Cascade(CascadeType.ALL)
	@JoinTable(name = "AgreedUser", joinColumns = {@JoinColumn(name = "contractId")}, inverseJoinColumns = {@JoinColumn(name = "userId")})
	private Set<User> agreedUsers;	//동의한 사용자들.

	public Integer getRequesterPenalty() {
		return requesterPenalty;
	}

	public void setRequesterPenalty(Integer requesterPenalty) {
		this.requesterPenalty = requesterPenalty;
	}

	public Integer getQuesterPenalty() {
		return questerPenalty;
	}

	public void setQuesterPenalty(Integer questerPenalty) {
		this.questerPenalty = questerPenalty;
	}

	public Set<Provision> getProvisions() {
		return provisions;
	}

	public void setProvisions(Set<Provision> provisions) {
		this.provisions = provisions;
	}

	public Quest getQuest() {
		return quest;
	}

	public void setQuest(Quest quest) {
		this.quest = quest;
	}

	public Set<User> getAgreedUsers() {
		return agreedUsers;
	}

	public void setAgreedUsers(Set<User> agreedUsers) {
		this.agreedUsers = agreedUsers;
	}
}
