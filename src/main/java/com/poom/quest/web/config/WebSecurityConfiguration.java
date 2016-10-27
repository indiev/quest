package com.poom.quest.web.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.poom.quest.services.service.LoginService;
import com.poom.quest.web.security.LoginFailureHandler;
import com.poom.quest.web.security.LoginSuccessHandler;
import com.poom.quest.web.security.LogoutSuccessHandler;

@Configuration
@EnableWebSecurity
@ComponentScan(basePackages={"com.poom.quest.services.service"})
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	@Qualifier("loginService")
	private LoginService loginService;
	
//	@Bean
//	public ShaPasswordEncoder passwordEncode() {
//		return new ShaPasswordEncoder(256);
//	}
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//		auth.inMemoryAuthentication()
//		auth.userDetailsService(loginService).passwordEncoder(passwordEncode());
		auth.userDetailsService(loginService);
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/resources/**");
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		http
			.authorizeRequests()
				.antMatchers("/resources/**").permitAll()
//				.antMatchers("/project/**").hasRole("USER")
//				.antMatchers("/server/**").hasRole("USER")
//				.antMatchers("/setting/**").hasRole("USER")
				.antMatchers("/**").permitAll()
				.antMatchers("/login").anonymous()
				.anyRequest().authenticated()
				.and()
			.formLogin()
				.loginPage("/").permitAll()
				.loginProcessingUrl("/api/login").permitAll()
//				.failureUrl("/?failed")
//				.defaultSuccessUrl("/?success", true)
				.usernameParameter("name")
				.passwordParameter("password")
				.failureHandler(new LoginFailureHandler())
				.successHandler(new LoginSuccessHandler())
				.and()
			.logout()
				.logoutUrl("/api/logout")
				.logoutSuccessUrl("/?logout")
				.logoutSuccessHandler(new LogoutSuccessHandler())
				.and()
			.rememberMe()
				.and()
//			.addFilterAfter(new SessionTimeoutFilter(), ExceptionTranslationFilter.class)
			.sessionManagement().maximumSessions(3*60*60);
	}
}
