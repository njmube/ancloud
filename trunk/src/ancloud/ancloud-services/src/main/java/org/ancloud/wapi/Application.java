package org.ancloud.wapi;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.ancloud.fw.core.serviceimpl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.session.MapSessionRepository;
import org.springframework.session.config.annotation.web.http.EnableSpringHttpSession;
import org.springframework.session.web.http.HeaderHttpSessionStrategy;
import org.springframework.session.web.http.HttpSessionStrategy;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@SpringBootConfiguration
@ComponentScan(basePackages = { "org.medtech" })
//@EnableRedisHttpSession(maxInactiveIntervalInSeconds=60)
@EnableSpringHttpSession
@EnableAutoConfiguration
@EnableJpaRepositories(basePackages = { "org.medtech.repository" })
//@EntityScan(basePackages = { "org.medtech.domain" })
// @SpringBootApplication
@CrossOrigin(origins = "*", maxAge = 3600)
public class Application {
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Value("${database.driverClassName}")
	private String DB_DRIVER;

	@Value("${database.password}")
	private String DB_PASSWORD;

	@Value("${database.url}")
	private String DB_URL;

	@Value("${database.username}")
	private String DB_USERNAME;

	@Value("${hibernate.dialect}")
	private String HIBERNATE_DIALECT;
	
	@Value("${jpa.schema-generation}")
	private String JPA_SCHEMA_GENERATION;
	
	@Bean
	public MapSessionRepository sessionRepository() {
		return new MapSessionRepository();
	}

//	@Bean
//	public LettuceConnectionFactory connectionFactory() {
//		return new LettuceConnectionFactory();
//	}

	@Bean
	public UserDetailsService userDetailsServiceFactory() {
		return new UserDetailsServiceImpl();
	}

	@Bean
	public HttpSessionStrategy httpSessionStrategy() {
		return new HeaderHttpSessionStrategy();
	}

	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(DB_DRIVER);
		dataSource.setUrl(DB_URL);
		dataSource.setUsername(DB_USERNAME);
		dataSource.setPassword(DB_PASSWORD);
		return dataSource;
	}
	
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(final EntityManagerFactoryBuilder builder) {
		Map<String,String> jpaProperties = new HashMap<String,String>();
		jpaProperties.put("hibernate.dialect", HIBERNATE_DIALECT);
		jpaProperties.put("javax.persistence.schema-generation.database.action", JPA_SCHEMA_GENERATION);
		LocalContainerEntityManagerFactoryBean ret = builder
				.dataSource(dataSource())
				.packages("org.medtech.domain")
				.properties(jpaProperties)
				.build();

		return ret;
	}

	@Bean
	public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(emf);
		return transactionManager;
	}
	
}
