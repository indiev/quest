package com.poom.quest.services.model.abstractModel;

import java.util.Set;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import com.fasterxml.jackson.annotation.JsonIgnore;

@MappedSuperclass
public abstract class TreeModel<T extends GenericModel> extends GenericModel {

	private static final long serialVersionUID = 1L;
	 
	private Integer sequency;

	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@Cascade(CascadeType.MERGE)
	@JoinColumn(name = "parentId", referencedColumnName = "id")
	private T parent;
	
	@OneToMany(mappedBy="parent", fetch = FetchType.LAZY)
	@OrderBy("sequency DESC")
	private Set<T> childs;
	
	public Integer getSequency() {
		return sequency;
	}
	public void setSequency(Integer sequency) {
		this.sequency = sequency;
	}
	public T getParent() {
		return parent;
	}
	public void setParent(T parent) {
		this.parent = parent;
	}
	public Set<T> getChilds() {
		return childs;
	}
	public void setChilds(Set<T> childs) {
		this.childs = childs;
	}
}
