package com.poom.quest.services.config;

import javax.persistence.EntityManagerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@Configuration
@PropertySource("classpath:/config.properties")
@EnableCaching
@EnableTransactionManagement
@Import({MysqlDataSourceConfiguration.class})
@ComponentScan(basePackages = {"com.poom.quest.services.model", "com.poom.quest.services.repository"})
public class ServicesConfiguration {
	public static final String UPLOADS_DIRECTORY = "/";

	@Autowired
	@Qualifier("entityManager")
	EntityManagerFactory entityManagerFactory;
	
	
    @Primary
    @Bean(name="transactionManager")
    public PlatformTransactionManager transactionManager() throws Exception {
        return new JpaTransactionManager(entityManagerFactory);
    }
}
