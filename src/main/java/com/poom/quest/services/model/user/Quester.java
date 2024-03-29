package com.poom.quest.services.model.user;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

import org.springframework.data.annotation.CreatedBy;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.poom.quest.services.model.Area;
import com.poom.quest.services.model.Quest;
import com.poom.quest.services.model.Skill;
import com.poom.quest.services.model.Work;
import com.poom.quest.services.model.abstractModel.WithUserModel;

@Entity
public class Quester extends WithUserModel {

	private static final long serialVersionUID = 1L;

	@CreatedBy
	@JsonIgnore
	@OneToOne(fetch =  FetchType.LAZY)
	@JoinColumn(name = "userId", referencedColumnName="id")
	private User user;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "QuesterArea", joinColumns = {@JoinColumn(name = "questerId")}, inverseJoinColumns = {@JoinColumn(name = "areaId")})
	private Set<Area> areas;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "QuesterWork", joinColumns = {@JoinColumn(name = "questerId")}, inverseJoinColumns = {@JoinColumn(name = "workId")})
	private Set<Work> works;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "QuesterSkill", joinColumns = {@JoinColumn(name = "questerId")}, inverseJoinColumns = {@JoinColumn(name = "skillId")})
	private Set<Skill> skills;

	@JsonIgnore
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "QuesterQuest", joinColumns = {@JoinColumn(name = "questId")}, inverseJoinColumns = {@JoinColumn(name = "questerId")})
	private Set<Quest> quests;
	
	@JsonIgnore
	@ManyToMany(mappedBy = "quester", fetch = FetchType.LAZY)
	private Set<Applicant> applicants;
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Set<Area> getAreas() {
		return areas;
	}

	public void setAreas(Set<Area> areas) {
		this.areas = areas;
	}

	public Set<Work> getWorks() {
		return works;
	}

	public void setWorks(Set<Work> works) {
		this.works = works;
	}

	public Set<Skill> getSkills() {
		return skills;
	}

	public void setSkills(Set<Skill> skills) {
		this.skills = skills;
	}
	
	public Set<Quest> getQuests() {
		return quests;
	}

	public void setQuests(Set<Quest> quests) {
		this.quests = quests;
	}
	
	public Set<Applicant> getApplicants() {
		return applicants;
	}

	public void setApplicants(Set<Applicant> applicants) {
		this.applicants = applicants;
	}
}
