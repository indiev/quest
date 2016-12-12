package com.poom.quest.services.service;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.poom.quest.services.domain.user.User;

@Service
@Transactional
public class UserService extends GenericService<User, Long> {
	
	public User getLoginUserByRequest() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if(auth == null || auth instanceof AnonymousAuthenticationToken) return null;
		String name = auth.getName();
		return this.getByKey("name", name);
	}
}
