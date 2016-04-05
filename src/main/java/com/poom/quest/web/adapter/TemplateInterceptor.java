package com.poom.quest.web.adapter;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class TemplateInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		return super.preHandle(request, response, handler);
	}
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		if((request.getHeader("x-requested-with") == null || !request.getHeader("x-requested-with").toLowerCase().equals("xmlhttprequest")) && request.getHeader("origin") == null && request.getHeader("x-pjax") == null && !modelAndView.getViewName().equals("main")) {
			modelAndView.getModelMap().addAttribute("mainContent", modelAndView.getViewName());
			modelAndView.setViewName("main");
		}
		super.postHandle(request, response, handler, modelAndView);
	}
}
