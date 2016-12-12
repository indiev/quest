package com.poom.quest.web.config;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.data.repository.support.DomainClassConverter;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.data.web.SortHandlerMethodArgumentResolver;
import org.springframework.format.support.FormattingConversionService;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;
import com.poom.quest.web.adapter.TemplateInterceptor;


@Configuration
/*@EnableSpringDataWebSupport
@Import(ServicesConfiguration.class)*/
@ComponentScan(basePackages={"com.poom.quest.web.controller", "com.poom.quest.services.model"})
//@EnableHypermediaSupport(type = { null })
public class WebAppConfiguration extends WebMvcConfigurationSupport {
	
	@Bean
	public MultipartResolver multipartResolver() {
		return new StandardServletMultipartResolver();
	}
	
    @Bean
    public InternalResourceViewResolver internalResourceViewResolver() {
        InternalResourceViewResolver internalResourceViewResolver = new InternalResourceViewResolver();
        internalResourceViewResolver.setViewClass(JstlView.class);
        internalResourceViewResolver.setPrefix("/WEB-INF/views/");
        internalResourceViewResolver.setSuffix(".jsp");
        internalResourceViewResolver.setOrder(1);
        return internalResourceViewResolver;
    }

    @Bean
    public MessageSource messageSource() {
        String[] baseNames = "messages".split(",");
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasenames(baseNames);
        messageSource.setUseCodeAsDefaultMessage(true);
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }
    
    @Bean
    public DomainClassConverter<?> domainClassConverter() {
    	return new DomainClassConverter<FormattingConversionService>(mvcConversionService());
    }

    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
    	super.addResourceHandlers(registry);
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
    }
    
    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
    	super.addInterceptors(registry);
    	registry.addInterceptor(new TemplateInterceptor());
    }
    
    @Override
    protected void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
    	super.addArgumentResolvers(argumentResolvers);
    	argumentResolvers.add(new PageableHandlerMethodArgumentResolver(new SortHandlerMethodArgumentResolver()));
    }
    
    @Override
    protected void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
    	super.configureDefaultServletHandling(configurer);
        configurer.enable();
    }
    
/*    @Override
    @Bean
	public RequestMappingHandlerMapping requestMappingHandlerMapping() {
    	RequestMappingHandlerMapping handler = super.requestMappingHandlerMapping();
    	handler.setOrder(1);
    	return handler;
    }
*/
    /*@Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
    	super.configureMessageConverters(converters);
    	MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();
    	List<MediaType> supportedMediaTypes = new ArrayList<MediaType>();
    	supportedMediaTypes.add(new MediaType("application", "json", StandardCharsets.UTF_8));
    	supportedMediaTypes.add(new MediaType("application", "x-www-form-urlencoded", StandardCharsets.UTF_8));
    	mappingJackson2HttpMessageConverter.setSupportedMediaTypes(supportedMediaTypes);
    	Jackson2ObjectMapperBuilder jackson2ObjectMapperBuilder = new Jackson2ObjectMapperBuilder();
    	jackson2ObjectMapperBuilder.indentOutput(true);
    	mappingJackson2HttpMessageConverter.setObjectMapper(jackson2ObjectMapperBuilder.build().registerModule(new Hibernate5Module()));
    	converters.add(new MappingJackson2HttpMessageConverter());
	}*/
}
