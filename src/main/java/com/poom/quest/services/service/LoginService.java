package com.poom.quest.services.service;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.poom.quest.services.model.user.User;

@Component("loginService")
public class LoginService implements UserDetailsService {

	@Autowired UserService userService;
	
	@Override
	public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
		UserDetails userDetails = null;
		try {
			User user = userService.getByKey("name", name);
			if(user != null) {
				userDetails = new org.springframework.security.core.userdetails.User(user.getName(), user.getPassword(), true, true, true, true, getAuthorities());
			}
		} catch (UsernameNotFoundException e){ 
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return userDetails;
	}
	
	public Collection<GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> authList = new ArrayList<GrantedAuthority>();
		authList.add(new SimpleGrantedAuthority("ROLE_USER"));
		return authList;
	}
}
