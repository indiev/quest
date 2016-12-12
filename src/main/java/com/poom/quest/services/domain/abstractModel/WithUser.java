package com.poom.quest.services.domain.abstractModel;

import com.poom.quest.services.domain.user.User;

public interface WithUser {
	public User getUser();
	public void setUser(User user);
}
