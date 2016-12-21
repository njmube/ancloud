package org.ancloud.services;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.UUID;

import javax.persistence.EntityManagerFactory;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import javax.transaction.TransactionManager;

import org.ancloud.fw.core.serviceimpl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.session.web.http.HeaderHttpSessionStrategy;
import org.springframework.session.web.http.HttpSessionStrategy;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@SpringBootConfiguration
@ComponentScan(basePackages = { "org.ancloud" })
@EnableRedisHttpSession
@EnableAutoConfiguration
@EnableJpaRepositories(basePackages = { "org.ancloud.repository" })
//@EntityScan(basePackages = { "org.ancloud.domain" })
// @SpringBootApplication
@RestController
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
	
	@Value("${hibernate.dialect}")
	private String JPA_SCHEMA_GENERATION;
	
	@RequestMapping(value="/",method = {RequestMethod.GET,RequestMethod.POST})
	public String hello(HttpSession session) {
		UUID uid = (UUID) session.getAttribute("uid");
		if (uid == null) {
			uid = UUID.randomUUID();
		}
		session.setAttribute("uid", uid);
		return uid.toString();
	}

	@Bean
	public LettuceConnectionFactory connectionFactory() {
		return new LettuceConnectionFactory();
	}

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

//	@Bean
//	public LocalSessionFactoryBean sessionFactory() {
//		LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();
//		sessionFactoryBean.setDataSource(dataSource());
//		Properties hibernateProperties = new Properties();
//		hibernateProperties.put("hibernate.dialect", HIBERNATE_DIALECT);
//		sessionFactoryBean.setHibernateProperties(hibernateProperties);
//		return sessionFactoryBean;
//	}
	
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(final EntityManagerFactoryBuilder builder) {
		Map<String,String> jpaProperties = new HashMap<String,String>();
		jpaProperties.put("hibernate.dialect", HIBERNATE_DIALECT);
		LocalContainerEntityManagerFactoryBean ret = builder
				.dataSource(dataSource())
				.packages("org.ancloud.domain")
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
	@Bean
	public TaskScheduler taskScheduler(){
		return new ThreadPoolTaskScheduler();
	}
}
