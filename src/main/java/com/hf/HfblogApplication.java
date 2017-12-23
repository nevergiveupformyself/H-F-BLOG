package com.hf;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

//@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class,
//						DataSourceTransactionManagerAutoConfiguration.class,
//						HibernateJpaAutoConfiguration.class})//在未配置db之前,先取消数据库的自动配置
@MapperScan(basePackages = "com.hf.dao")//mapper扫描包路径
@EnableTransactionManagement//启动事物注解
@SpringBootApplication
public class HfblogApplication {
	public static void main(String[] args) {
		SpringApplication.run(HfblogApplication.class, args);
	}
}
