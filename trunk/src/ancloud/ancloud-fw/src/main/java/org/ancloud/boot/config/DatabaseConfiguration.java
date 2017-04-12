package org.ancloud.boot.config;

import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

import javax.inject.Inject;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.ancloud.fw.core.dbmigration.CustomFlywayMigrationStrategy;
import org.apache.commons.dbcp2.BasicDataSource;
import org.joda.time.DateTimeZone;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.hibernate5.HibernateExceptionTranslator;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@EnableJpaRepositories(basePackages = { "org.ancloud.repository" })
public class DatabaseConfiguration {

	@Inject
	ApplicationProperties applicationProperties;

	@Bean
	public DataSource dataSource() {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName(applicationProperties.getDatabase().getDriverClassName());
		dataSource.setUrl(applicationProperties.getDatabase().getUrl());
		dataSource.setUsername(applicationProperties.getDatabase().getUserName());
		dataSource.setPassword(applicationProperties.getDatabase().getPassword());
		dataSource.setDefaultAutoCommit(false);
		dataSource.setMaxTotal(applicationProperties.getCp().getMaxActive());
		dataSource.setMaxIdle(applicationProperties.getCp().getMaxIdle());
		dataSource.setMaxWaitMillis(applicationProperties.getCp().getMaxWaitMillis());
		dataSource.setMinIdle(applicationProperties.getCp().getMinIdle());
		return dataSource;
	}

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(final EntityManagerFactoryBuilder builder) {
		Map<String, String> jpaProperties = new HashMap<String, String>();
		jpaProperties.put("hibernate.dialect", applicationProperties.getDatabase().getDialect());
		// jpaProperties.put("hibernate.format_sql", );

		jpaProperties.put("javax.persistence.schema-generation.database.action",
				applicationProperties.getJpa().getSchemaGeneration());
		jpaProperties.put("javax.persistence.schema-generation.scripts.action",
				applicationProperties.getJpa().getSchemaScriptGeneration());
		jpaProperties.put("javax.persistence.schema-generation.scripts.create-target",
				applicationProperties.getJpa().getSchemaScriptGenerationCreateTarget());
		jpaProperties.put("javax.persistence.schema-generation.scripts.drop-target",
				applicationProperties.getJpa().getSchemaScriptGenerationDropTarget());
		jpaProperties.put("jadira.usertype.autoRegisterUserTypes", "true");
		jpaProperties.put("jadira.usertype.databaseZone", "UTC");
		jpaProperties.put("jadira.usertype.javaZone", "UTC");
		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
		DateTimeZone.setDefault(DateTimeZone.UTC);
		LocalContainerEntityManagerFactoryBean entityManagerFactory = builder.dataSource(dataSource()).packages("org.ancloud.domain")
				.properties(jpaProperties).build();
		
		HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
		jpaVendorAdapter.setShowSql(applicationProperties.getDatabase().getShowSql());
		entityManagerFactory.setJpaVendorAdapter(jpaVendorAdapter);
		return entityManagerFactory;
	}

	@Bean
	public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(emf);
		return transactionManager;
	}
	@Bean
	public CustomFlywayMigrationStrategy customFlywayMigrationStrategy(){
		return new CustomFlywayMigrationStrategy();
	}
	
	@Bean
	public HibernateExceptionTranslator exceptionTranslation() {
		return new HibernateExceptionTranslator();
	}
}
