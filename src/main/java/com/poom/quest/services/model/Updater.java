package com.poom.quest.services.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.poom.quest.services.model.abstractModel.GenericModel;
import com.poom.quest.services.model.user.User;

@Entity
public class Updater extends GenericModel {

	private static final long serialVersionUID = 1L;
	
	private String refModel;
	private String refColumn;
	private Integer refId;
	private String origin;
	private Character type;
	private Character approval;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "userId", referencedColumnName = "id")
	private User user;
	
	public String getRefModel() {
		return refModel;
	}
	public void setRefModel(String refModel) {
		this.refModel = refModel;
	}
	public String getRefColumn() {
		return refColumn;
	}
	public void setRefColumn(String refColumn) {
		this.refColumn = refColumn;
	}
	public Integer getRefId() {
		return refId;
	}
	public void setRefId(Integer refId) {
		this.refId = refId;
	}
	public String getOrigin() {
		return origin;
	}
	public void setOrigin(String origin) {
		this.origin = origin;
	}
	public Character getType() {
		return type;
	}
	public void setType(Character type) {
		this.type = type;
	}
	public Character getApproval() {
		return approval;
	}
	public void setApproval(Character approval) {
		this.approval = approval;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
}
