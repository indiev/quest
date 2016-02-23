package com.poom.quest.web.security;

import java.util.List;

import org.springframework.context.ApplicationListener;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.session.SessionDestroyedEvent;
import org.springframework.security.core.userdetails.UserDetails;

public class SessionDestroyListener implements ApplicationListener<SessionDestroyedEvent> {

	@Override
	public void onApplicationEvent(SessionDestroyedEvent event) {
		List<SecurityContext> contexts = event.getSecurityContexts();
		if(!contexts.isEmpty()) {
			for(SecurityContext context : contexts) {
				@SuppressWarnings("unused")
				UserDetails userDetails = (UserDetails) context.getAuthentication().getPrincipal();
				//로그아웃 정보 저장
			}
		}
	}
	
}
