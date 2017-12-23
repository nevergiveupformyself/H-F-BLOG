package com.hf.config;

import liquibase.integration.spring.SpringLiquibase;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * Created by fjm on 2017/12/22.
 */
@Configuration
public class LiquiBaseConfig {

    @Value("${liquibase.run:false}")
    private Boolean isShouldRun;

    @Bean
    public SpringLiquibase liquibase(DataSource dataSource){
        SpringLiquibase liquibase = new SpringLiquibase();
        liquibase.setDataSource(dataSource);
        liquibase.setChangeLog("classpath:db/liquibase/master.xml");
        liquibase.setContexts("development,test,production");
        liquibase.setShouldRun(isShouldRun);
        return liquibase;
    }
}
