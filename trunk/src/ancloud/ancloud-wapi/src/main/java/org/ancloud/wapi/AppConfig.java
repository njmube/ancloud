package org.ancloud.wapi;

import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.joda.time.DateTimeZone;
import org.ancloud.fw.core.dbmigration.CustomFlywayMigrationStrategy;
import org.ancloud.service.authentication.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.hibernate5.HibernateExceptionTranslator;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ScheduledExecutorFactoryBean;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.session.web.http.HeaderHttpSessionStrategy;
import org.springframework.session.web.http.HttpSessionStrategy;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootConfiguration
@ComponentScan(basePackages = { "org.ancloud" })
@EnableAutoConfiguration
@EnableJpaRepositories(basePackages = { "org.ancloud.repository" })
// @EntityScan(basePackages = { "org.ancloud.domain" })
// @SpringBootApplication
@CrossOrigin(origins = "*", maxAge = 3600)
@EnableScheduling
public class AppConfig {
	
	@Value("${ancloud.database.driverClassName}")
	private String DB_DRIVER;

	@Value("${ancloud.database.password}")
	private String DB_PASSWORD;

	@Value("${ancloud.database.url}")
	private String DB_URL;

	@Value("${ancloud.database.username}")
	private String DB_USERNAME;
	
	@Value("${ancloud.cp.maxActive}")
	private Integer CP_MAX_TOTAL;
	
	@Value("${ancloud.cp.maxIdle}")
	private Integer CP_MAX_IDLE;

	@Value("${ancloud.cp.minIdle}")
	private Integer CP_MIN_IDLE;
	
	@Value("${ancloud.cp.maxWait}")
	private Integer CP_MAX_WAIT;

	@Value("${ancloud.hibernate.dialect}")
	private String HIBERNATE_DIALECT;
	
	@Value("${ancloud.hibernate.showSql}")
	private Boolean HIBERNATE_SHOW_SQL;

	@Value("${ancloud.jpa.schema-generation}")
	private String JPA_SCHEMA_GENERATION;
	
	@Value("${ancloud.jpa.schema-generation.scripts}")
	private String JPA_SCHEMA_GENERATION_SCRIPTS;
	
	@Value("${ancloud.jpa.schema-generation.scripts.create-target}")
	private String JPA_SCHEMA_GENERATION_SCRIPTS_CREATE;
	
	@Value("${ancloud.jpa.schema-generation.scripts.drop-target}")
	private String JPA_SCHEMA_GENERATION_SCRIPTS_DROP;
	
	@Value("${spring.redis.host}")
	private String REDIS_HOST;

	@Value("${spring.redis.port}")
	private int REDIS_PORT;

	@Bean
	public UserDetailsService userDetailsServiceFactory() {
		return new UserDetailsServiceImpl();
	}

	@Bean
	public HttpSessionStrategy httpSessionStrategy() {
		return new HeaderHttpSessionStrategy();
	}

//	@Bean
//	public DataSource dataSource() {
//		DriverManagerDataSource dataSource = new DriverManagerDataSource();
//		dataSource.setDriverClassName(DB_DRIVER);
//		dataSource.setUrl(DB_URL);
//		dataSource.setUsername(DB_USERNAME);
//		dataSource.setPassword(DB_PASSWORD);
//		return dataSource;
//	}
	
	@Bean
	public DataSource dataSource() {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName(DB_DRIVER);
		dataSource.setUrl(DB_URL);
		dataSource.setUsername(DB_USERNAME);
		dataSource.setPassword(DB_PASSWORD);
		dataSource.setDefaultAutoCommit(false);
		dataSource.setMaxTotal(CP_MAX_TOTAL);
		dataSource.setMaxIdle(CP_MAX_IDLE);
		dataSource.setMaxWaitMillis(CP_MAX_WAIT);
		return dataSource;
	}
	
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(final EntityManagerFactoryBuilder builder) {
		Map<String, String> jpaProperties = new HashMap<String, String>();
		jpaProperties.put("hibernate.dialect", HIBERNATE_DIALECT);
//		jpaProperties.put("hibernate.format_sql", );
		
		jpaProperties.put("javax.persistence.schema-generation.database.action", JPA_SCHEMA_GENERATION);
		jpaProperties.put("javax.persistence.schema-generation.scripts.action", JPA_SCHEMA_GENERATION_SCRIPTS);
		jpaProperties.put("javax.persistence.schema-generation.scripts.create-target", JPA_SCHEMA_GENERATION_SCRIPTS_CREATE);
		jpaProperties.put("javax.persistence.schema-generation.scripts.drop-target", JPA_SCHEMA_GENERATION_SCRIPTS_DROP);
		jpaProperties.put("jadira.usertype.autoRegisterUserTypes","true");
		jpaProperties.put("jadira.usertype.databaseZone", "UTC");
		jpaProperties.put("jadira.usertype.javaZone","UTC" );
		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
		DateTimeZone.setDefault(DateTimeZone.UTC);
		LocalContainerEntityManagerFactoryBean ret = builder.dataSource(dataSource())
																.packages("org.ancloud.domain")
																.properties(jpaProperties).build();
		HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
		jpaVendorAdapter.setShowSql(HIBERNATE_SHOW_SQL);
		ret.setJpaVendorAdapter(jpaVendorAdapter);
		return ret;
	}

	@Bean
	public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(emf);
		return transactionManager;
	}

	@Bean
	public ScheduledExecutorFactoryBean scheduledExecutorService() {
		return new ScheduledExecutorFactoryBean();
	}

	@Bean
	public TaskScheduler taskScheduler() {
		return new ThreadPoolTaskScheduler ();
	}

	@Bean
	public HibernateExceptionTranslator exceptionTranslation() {
		return new HibernateExceptionTranslator();
	}
	
	@Bean
	public CustomFlywayMigrationStrategy customFlywayMigrationStrategy(){
		return new CustomFlywayMigrationStrategy();
	}
	

}
