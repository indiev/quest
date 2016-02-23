package com.poom.quest.services.model.abstractModel;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

@MappedSuperclass
public abstract class TreeModel<T> extends GenericModel {

	private static final long serialVersionUID = 1L;
	
	private Integer parentId;
	private Integer sequency;
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "parentId", referencedColumnName="id", insertable=false, updatable=false)
	@OrderBy("sequency DESC")
	private List<T> childs;
	
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
	public List<T> getChilds() {
		return childs;
	}
	public void setChilds(List<T> childs) {
		this.childs = childs;
	}
}
