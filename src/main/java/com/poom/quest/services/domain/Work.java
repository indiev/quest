package com.poom.quest.services.domain;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import com.poom.quest.services.domain.abstractModel.TreeModel;

@Entity
public class Work extends TreeModel<Work> {

	private static final long serialVersionUID = 1L;
	
	private String description;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "WorkSkill", joinColumns = {@JoinColumn(name = "workId")}, inverseJoinColumns = {@JoinColumn(name = "skillId")})
	private Set<Skill> skills;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<Skill> getSkills() {
		return skills;
	}

	public void setSkills(Set<Skill> skills) {
		this.skills = skills;
	}
	
}
