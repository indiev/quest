package com.poom.quest.services.model.abstractModel;

import com.poom.quest.services.model.user.User;

public interface WithUser {
	public User getUser();
	public void setUser(User user);
}
