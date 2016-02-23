package com.poom.quest.services.config;


import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.PersistenceUnit;
import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.hibernate.dialect.MySQL5Dialect;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import com.mysql.jdbc.Driver;
import com.poom.quest.services.model.Model;

@Configuration
@Profile("default")
public class MysqlDataSourceConfiguration   {

	@Autowired
	@Qualifier("localDataSource")
	DataSource localDataSource;
	
    @Bean(name="localDataSource")
    public DataSource dataSource( Environment environment ) throws Exception {
        String user = environment.getProperty("ds.user"),
                pw = environment.getProperty("ds.password"),
                url = environment.getProperty("ds.url");
        Class<Driver> driverClass = environment.getPropertyAsClass( "ds.driverClass", Driver.class );

        return getDataSource(user, pw, url, driverClass);
    }

    @Primary
    @Bean(name = "localEntityManager")
    @PersistenceUnit(name = "localEntityManagerUnit")
    public LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean() throws Exception {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setBeanName("localEntityManager");
        em.setPersistenceUnitName("localEntityManagerUnit");
        em.setDataSource( localDataSource );
        em.setPackagesToScan(Model.class.getPackage().getName());
        em.setPersistenceProvider(new HibernatePersistenceProvider());
        Map<String, String> p = new HashMap<String, String>();
        p.put(org.hibernate.cfg.Environment.DEFAULT_BATCH_FETCH_SIZE, "4"); // 4, 8, 16
        p.put(org.hibernate.cfg.Environment.SHOW_SQL, "false");
        p.put(org.hibernate.cfg.Environment.FORMAT_SQL, "false");
        p.put(org.hibernate.cfg.Environment.POOL_SIZE, "20");
        p.put(org.hibernate.cfg.Environment.DIALECT, MySQL5Dialect.class.getName());
        em.setJpaPropertyMap(p);
        return em;
    }
    
    @Bean
    public CacheManager cacheManager() throws Exception {
        SimpleCacheManager scm = new SimpleCacheManager();
        Cache userCache = new ConcurrentMapCache("users");
        Cache questCache = new ConcurrentMapCache("quests");
        scm.setCaches(Arrays.asList(userCache, questCache));
        return scm;
    }
    
    public DataSource getDataSource(String user, String pw, String url, Class<Driver> driverClass) {
    	BasicDataSource basicDataSource = new BasicDataSource();
        basicDataSource.setDriverClassName( driverClass.getName() );
        basicDataSource.setPassword( pw );
        basicDataSource.setUrl( url );
        basicDataSource.setUsername( user );
        basicDataSource.setInitialSize( 100 );
        basicDataSource.setMaxActive( 200 );
        basicDataSource.setValidationQuery("select 1");
        basicDataSource.setValidationQueryTimeout(30000);
        basicDataSource.setTestOnBorrow(true);
        basicDataSource.setTestWhileIdle(true);
        basicDataSource.setTestOnReturn(false);
        basicDataSource.setTimeBetweenEvictionRunsMillis(30000);
        basicDataSource.setRemoveAbandoned(true);
        return basicDataSource;
    }

}
