package com.poom.quest.services.config;


import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.PersistenceUnit;
import javax.sql.DataSource;

import org.hibernate.dialect.H2Dialect;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import com.poom.quest.services.model.Model;

//@Configuration
//@Profile("default")
public class LocalDataSourceConfiguration   {


    @Bean
    public DataSource dataSource( Environment environment ) throws Exception {
        return new EmbeddedDatabaseBuilder()
                .setName("crm")
                .setType(EmbeddedDatabaseType.H2)
                .build();
    }

    @Primary
    @Bean(name = "localEntityManager")
    @PersistenceUnit(name = "localEntityManagerUnit")
    public LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean( DataSource dataSource  ) throws Exception {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setPersistenceUnitName("localEntityManagerUnit");
        em.setDataSource( dataSource );
        em.setPackagesToScan(Model.class.getPackage().getName());
        em.setPersistenceProvider(new HibernatePersistenceProvider());
        Map<String, String> p = new HashMap<String, String>();
        p.put(org.hibernate.cfg.Environment.HBM2DDL_AUTO, "create");
        p.put(org.hibernate.cfg.Environment.HBM2DDL_IMPORT_FILES, "import_h2.sql");
        p.put(org.hibernate.cfg.Environment.DIALECT, H2Dialect.class.getName());
        p.put(org.hibernate.cfg.Environment.SHOW_SQL, "true");
        em.setJpaPropertyMap(p);
        return em;
    }

    @Bean
    public CacheManager cacheManager() throws Exception {
        SimpleCacheManager scm = new SimpleCacheManager();
        Cache serviceCache = new ConcurrentMapCache("services");
        scm.setCaches(Arrays.asList(serviceCache));
        return scm;
    }

}
