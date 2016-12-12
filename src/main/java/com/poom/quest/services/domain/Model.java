package com.poom.quest.services.domain;

import java.io.Serializable;

import javax.persistence.MappedSuperclass;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@MappedSuperclass
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public abstract class Model implements Serializable {
	
	private static final long serialVersionUID = 1L;
}
