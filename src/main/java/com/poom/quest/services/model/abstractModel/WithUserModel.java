package com.poom.quest.services.model.abstractModel;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class WithUserModel extends GenericModel implements WithUser {
	private static final long serialVersionUID = 1L;
}
