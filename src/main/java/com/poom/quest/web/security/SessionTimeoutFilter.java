package com.poom.quest.web.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import org.apache.log4j.Logger;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.authentication.AuthenticationTrustResolverImpl;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.util.ThrowableAnalyzer;
import org.springframework.security.web.util.ThrowableCauseExtractor;
import org.springframework.web.filter.GenericFilterBean;


public class SessionTimeoutFilter /*extends GenericFilterBean*/ {
	
//	private Logger log = Logger.getLogger(getClass());
//	
//	private ThrowableAnalyzer throwableAnalyzer = new DefaultThrowableAnalyzer();
//	private AuthenticationTrustResolver authenticationTrustResolver = new AuthenticationTrustResolverImpl();
//	
//	private int customSessionExpiredErrorCode = 901; 
//
//	@Override
//	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
//		try {
//			filterChain.doFilter(request, response);
//			log.debug("Chain Processed normally");
//		} catch (IOException e) {
//			throw e;
//		} catch (Exception e) {
//			Throwable[] causeChain = throwableAnalyzer.determineCauseChain(e);
//			RuntimeException runtimeException = (AuthenticationException)throwableAnalyzer.getFirstThrowableOfType(AuthenticationException.class, causeChain);
//			if(runtimeException == null) runtimeException = (AccessDeniedException) throwableAnalyzer.getFirstThrowableOfType(AccessDeniedException.class, causeChain);
//			if(runtimeException != null) {
//				if(runtimeException instanceof AuthenticationException) {
//					throw runtimeException;
//				} else if(runtimeException instanceof AccessDeniedException) {
//					HttpServletRequest httpServletRequest = (HttpServletRequest) request;
//					HttpServletResponse httpServletResponse = (HttpServletResponse) response;
//					if(authenticationTrustResolver.isAnonymous(SecurityContextHolder.getContext().getAuthentication())) {
//						log.info("User session expired or not logged in yet");
//						String ajaxHeader = (httpServletRequest).getHeader("X-Requested-With");
//						
//						if("XMLHttpRequest".equals(ajaxHeader)) {
//							log.info("Ajax call detected, send {} error code");
//							httpServletResponse.sendError(this.customSessionExpiredErrorCode);
//						} else {
//							log.info("Redirect to login page");
//							throw runtimeException;
//						}
//					} else {
//						throw runtimeException;
//					}
//				}
//			}
//		}
//	}
//
//	public static final class DefaultThrowableAnalyzer extends ThrowableAnalyzer {
//		@Override
//		protected void initExtractorMap() {
//			super.initExtractorMap();
//			registerExtractor(ServletException.class, new ThrowableCauseExtractor() {
//				@Override
//				public Throwable extractCause(Throwable throwable) {
//					ThrowableAnalyzer.verifyThrowableHierarchy(throwable, ServletException.class);
//					return ((ServletException)throwable).getRootCause();
//				}
//			});
//		}
//	}
//	
//	public void setCustomSessionExpiredErrorCode(int customSessionExpiredErrorCode) {
//		this.customSessionExpiredErrorCode = customSessionExpiredErrorCode;
//	}
}
