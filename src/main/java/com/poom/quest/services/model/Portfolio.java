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
import com.poom.quest.services.model.user.Quester;
import com.poom.quest.services.model.user.User;

@Entity
public class Portfolio extends GenericModel {

	private static final long serialVersionUID = 1L;
	
	private String target;
	private Date startDate;
	private Date endDate;
	private String Description;
	
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
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "userId", referencedColumnName = "id")
	private User user;
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "questerId", referencedColumnName = "id")
	private Quester quester;

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
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
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public Code getType() {
		return type;
	}

	public void setType(Code type) {
		this.type = type;
	}

	public Quester getQuester() {
		return quester;
	}

	public void setQuester(Quester quester) {
		this.quester = quester;
	}
}
