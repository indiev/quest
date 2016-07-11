package com.poom.quest.services.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import com.poom.quest.services.model.abstractModel.GenericModel;
import com.poom.quest.services.model.user.Quester;
import com.poom.quest.services.model.user.Requester;

@Entity
public class Quest extends GenericModel {

	private static final long serialVersionUID = 1L;

	private String qualification;	//자격
	private Date recruitmentEndDate; //마감일
	private Integer duration; //수행기간(일)
	private String description; //설명 description
	//sub Project?

	@OneToMany(mappedBy = "quest", fetch = FetchType.LAZY)
	@Cascade(CascadeType.ALL)
	private Set<Reward> rewards; //보상 reward 경험치 point 물건? model로 따로 뺌? rewardPoint, rewardExperiencePoint other
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "stateId", referencedColumnName = "id")
	private Code state; //상태 - 준비(R), 요청자를 기다림(wait), 토의(discuss), 진행(progress), 완료(complete), 중지(stop), 일시정지(pause)
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "QuestArea", joinColumns = {@JoinColumn(name = "questId")}, inverseJoinColumns = {@JoinColumn(name = "areaId")})
	private Set<Area> areas;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "QuestWork", joinColumns = {@JoinColumn(name = "questId")}, inverseJoinColumns = {@JoinColumn(name = "workId")})
	private Set<Work> works;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "QuestSkill", joinColumns = {@JoinColumn(name = "questId")}, inverseJoinColumns = {@JoinColumn(name = "skillId")})
	private Set<Skill> skills;
	
	@OneToOne(mappedBy = "quest", fetch = FetchType.LAZY)
	@Cascade(CascadeType.ALL)
	private Contract contract;

	@OneToMany(mappedBy = "quest", fetch = FetchType.LAZY)
	@Cascade(CascadeType.ALL)
	private Set<Requirement> requirements; //요구사항들
	
	@OneToMany(mappedBy = "quest", fetch = FetchType.LAZY)
	@Cascade(CascadeType.ALL)
	private Set<Issue> issues; //이슈
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "requesterId", referencedColumnName = "id")
	private Requester requester;	//발주자
	 
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "QuesterQuest", joinColumns = {@JoinColumn(name = "questId")}, inverseJoinColumns = {@JoinColumn(name = "questerId")})
	private Set<Quester> questers;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "ApplicantQuest", joinColumns = {@JoinColumn(name = "questId")}, inverseJoinColumns = {@JoinColumn(name = "questerId")})
	private Set<Quester> applicants;

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

	public Set<Reward> getRewards() {
		return rewards;
	}

	public void setRewards(Set<Reward> rewards) {
		this.rewards = rewards;
	}

	public Code getState() {
		return state;
	}

	public void setState(Code state) {
		this.state = state;
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

	public Contract getContract() {
		return contract;
	}

	public void setContract(Contract contract) {
		this.contract = contract;
	}

	public Set<Requirement> getRequirements() {
		return requirements;
	}

	public void setRequirements(Set<Requirement> requirements) {
		this.requirements = requirements;
	}
	
	public Set<Issue> getIssues() {
		return issues;
	}

	public void setIssues(Set<Issue> issues) {
		this.issues = issues;
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
