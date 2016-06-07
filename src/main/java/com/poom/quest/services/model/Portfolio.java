package com.poom.quest.services.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.poom.quest.services.model.abstractModel.GenericModel;
import com.poom.quest.services.model.user.User;

@Entity
public class Portfolio extends GenericModel {

	private static final long serialVersionUID = 1L;
	
	private String place;
	private String level;
	private String result;
	private Date startDate;
	private Date endDate;
	private String description;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "typeId", referencedColumnName = "id")
	private Code type;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "PortfolioArea", joinColumns = {@JoinColumn(name = "portfolioId")}, inverseJoinColumns = {@JoinColumn(name = "areaId")})
	private Set<Area> areas;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "PortfolioWork", joinColumns = {@JoinColumn(name = "portfolioId")}, inverseJoinColumns = {@JoinColumn(name = "workId")})
	private Set<Work> works;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "PortfolioSkill", joinColumns = {@JoinColumn(name = "portfolioId")}, inverseJoinColumns = {@JoinColumn(name = "skillId")})
	private Set<Skill> skills;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "SubPortfolio", joinColumns = {@JoinColumn(name = "portfolioId")}, inverseJoinColumns = {@JoinColumn(name = "subPortfolioId")})
	private Set<Portfolio> subPortfolios;
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "userId", referencedColumnName = "id")
	private User user;

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Code getType() {
		return type;
	}

	public void setType(Code type) {
		this.type = type;
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

	public Set<Portfolio> getSubPortfolios() {
		return subPortfolios;
	}

	public void setSubPortfolios(Set<Portfolio> subPortfolios) {
		this.subPortfolios = subPortfolios;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
