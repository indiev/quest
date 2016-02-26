package com.poom.quest.services.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.poom.quest.services.model.abstractModel.GenericModel;

@Entity
public class Area extends GenericModel {

	private static final long serialVersionUID = 1L;

	@JsonIgnore
	@OneToMany(mappedBy = "area", fetch = FetchType.LAZY)
	private Set<Kind> kinds;

	public Set<Kind> getKinds() {
		return kinds;
	}

	public void setKinds(Set<Kind> kinds) {
		this.kinds = kinds;
	}
}
