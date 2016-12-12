package com.poom.quest.services.domain.abstractModel;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class WithUserModel extends Domain implements WithUser {
	private static final long serialVersionUID = 1L;
}
