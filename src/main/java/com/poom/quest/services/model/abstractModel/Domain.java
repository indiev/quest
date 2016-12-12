package com.poom.quest.services.model.abstractModel;

import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import com.poom.quest.services.domain.Model;

@MappedSuperclass
public abstract class Domain extends Model {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Long id;
	/*@NotNull*/
	protected String name;
	@CreatedDate
	@NotNull
	protected Date createdDate;
	@LastModifiedDate
	@NotNull
	protected Date modifiedDate;
	
	@PrePersist
	public void onCreate() {
		this.createdDate = new Date();
		this.modifiedDate = new Date();
	}
	
	@PreUpdate
	public void onUpdate() {
		this.modifiedDate = new Date();
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public Date getModifiedDate() {
		return modifiedDate;
	}
	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
}
