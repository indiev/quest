package com.poom.quest.services.domain.abstractModel;

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
public abstract class TreeModel<T extends TreeModel<T>> extends Domain {

	private static final long serialVersionUID = 1L;
	 
	protected Integer sequency;

	@ManyToOne(fetch = FetchType.LAZY)
	@Cascade(CascadeType.MERGE)
	@JoinColumn(name = "parentId", referencedColumnName = "id")
	protected T parent;
	
	@OneToMany(mappedBy="parent", fetch = FetchType.LAZY)
	@OrderBy("sequency DESC")
	protected Set<T> childs;
	
	public Integer getSequency() {
		return sequency;
	}
	public void setSequency(Integer sequency) {
		this.sequency = sequency;
	}
	@JsonIgnore
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
