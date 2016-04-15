package com.poom.quest.services.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.poom.quest.services.model.abstractModel.GenericModel;
import com.poom.quest.services.model.user.Quester;
import com.poom.quest.services.model.user.Requester;

@Entity
public class Quest extends GenericModel {

	private static final long serialVersionUID = 1L;
	
	private String reward; //보상 reward 경험치 point 물건? model로 따로 뺌? rewardPoint, rewardExperiencePoint other
	private String qualification;	//자격
	private Date recruitmentEndDate; //마감일
	private Integer duration; //수행기간(일)
	private String description; //설명 description
	//계약에 대한.. (패널티 - 위약금)
	//sub Project?
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "QuestArea", joinColumns = {@JoinColumn(name = "questId")}, inverseJoinColumns = {@JoinColumn(name = "areaId")})
	private Set<Area> areas;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "QuestWork", joinColumns = {@JoinColumn(name = "questId")}, inverseJoinColumns = {@JoinColumn(name = "workId")})
	private Set<Work> works;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "QuestSkill", joinColumns = {@JoinColumn(name = "questId")}, inverseJoinColumns = {@JoinColumn(name = "skillId")})
	private Set<Skill> skills;

	@OneToMany(mappedBy = "quest", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Requirement> requirements; //요구사항들
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "requesterId", referencedColumnName = "id")
	private Requester requester;	//발주자
	 
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "QuesterQuest", joinColumns = {@JoinColumn(name = "questId")}, inverseJoinColumns = {@JoinColumn(name = "questerId")})
	private Set<Quester> questers;
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "ApplicantQuest", joinColumns = {@JoinColumn(name = "questId")}, inverseJoinColumns = {@JoinColumn(name = "questerId")})
	private Set<Quester> applicants;
	
	public String getReward() {
		return reward;
	}

	public void setReward(String reward) {
		this.reward = reward;
	}

	public String getQualification() {
		return qualification;
	}

	public void setQualification(String qualification) {
		this.qualification = qualification;
	}

	public Date getRecruitmentEndDate() {
		return recruitmentEndDate;
	}

	public void setRecruitmentEndDate(Date recruitmentEndDate) {
		this.recruitmentEndDate = recruitmentEndDate;
	}

	public Integer getDuration() {
		return duration;
	}

	public void setDuration(Integer duration) {
		this.duration = duration;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public Set<Requirement> getRequirements() {
		return requirements;
	}

	public void setRequirements(Set<Requirement> requirements) {
		this.requirements = requirements;
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

	public Set<Quester> getApplicants() {
		return applicants;
	}

	public void setApplicants(Set<Quester> applicants) {
		this.applicants = applicants;
	}
}
