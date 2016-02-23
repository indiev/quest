package com.poom.quest.web.initializer;


import javax.servlet.Filter;
import javax.servlet.ServletContext;

import org.springframework.core.annotation.Order;
import org.springframework.orm.jpa.support.OpenEntityManagerInViewFilter;
import org.springframework.security.web.session.HttpSessionEventPublisher;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.multipart.support.MultipartFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import com.poom.quest.services.config.ServicesConfiguration;
import com.poom.quest.web.config.WebAppConfiguration;
import com.poom.quest.web.config.WebSecurityConfiguration;

@Order(1)
public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	private int maxuploadSizeInMb = 5 * 1024 * 1024; // 5 MB
	
	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class<?>[] { ServicesConfiguration.class, WebSecurityConfiguration.class/*, WebSocketConfiguration.class*/ };
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class<?>[] { WebAppConfiguration.class };
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}
	
	@Override
	protected Filter[] getServletFilters() {
		CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
		characterEncodingFilter.setEncoding("UTF-8");
		characterEncodingFilter.setForceEncoding(true);
		return new Filter[] { new OpenEntityManagerInViewFilter(), new HiddenHttpMethodFilter(), characterEncodingFilter, new MultipartFilter()};
	}
	
	@Override
	protected void registerDispatcherServlet(ServletContext servletContext) {
		super.registerDispatcherServlet(servletContext);
		servletContext.addListener(new HttpSessionEventPublisher());
	}
	
//	@Override
//	protected void customizeRegistration(ServletRegistration.Dynamic registration) { 
//		File uploadDirectory = new File(ServicesConfiguration.UPLOADS_DIRECTORY);
//		MultipartConfigElement multipartConfigElement = new MultipartConfigElement(uploadDirectory.getAbsolutePath(), maxuploadSizeInMb, maxuploadSizeInMb * 2, maxuploadSizeInMb);
//		
//		registration.setMultipartConfig(multipartConfigElement);
//	}
}
