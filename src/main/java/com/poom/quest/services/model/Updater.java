package com.poom.quest.services.model;

import javax.persistence.Entity;

import com.poom.quest.services.model.abstractModel.GenericModel;

@Entity
public class Updater extends GenericModel {

	private static final long serialVersionUID = 1L;
	
	private String model;
	private String column;
	private String origin;
	private Character type;
	private Character approval;
	
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getColumn() {
		return column;
	}
	public void setColumn(String column) {
		this.column = column;
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
}
