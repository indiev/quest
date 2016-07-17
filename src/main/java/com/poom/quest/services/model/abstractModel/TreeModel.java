package com.poom.quest.services.model.abstractModel;

import java.util.Set;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@MappedSuperclass
public abstract class TreeModel<T extends GenericModel> extends GenericModel {

	private static final long serialVersionUID = 1L;
	 
	private Integer parentId;
	private Integer sequency;
	@OneToMany(fetch = FetchType.EAGER)
	@Cascade(CascadeType.MERGE)
	@JoinColumn(name = "parentId", referencedColumnName="id")
	@OrderBy("sequency DESC")
	private Set<T> childs;
	
	public Integer getParentId() {
		return parentId;
	}
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
	public Integer getSequency() {
		return sequency;
	}
	public void setSequency(Integer sequency) {
		this.sequency = sequency;
	}
	public Set<T> getChilds() {
		return childs;
	}
	public void setChilds(Set<T> childs) {
		this.childs = childs;
	}
}
