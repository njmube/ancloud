package org.ancloud.boot.config;

import javax.inject.Inject;

import org.ancloud.fw.core.dbmigration.CustomFlywayMigrationStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.hibernate5.HibernateExceptionTranslator;

@Configuration
@EnableJpaRepositories(basePackages = { "org.ancloud.repository" })
public class DatabaseConfiguration {

	@Inject
	ApplicationProperties applicationProperties;

	@Bean
	public CustomFlywayMigrationStrategy customFlywayMigrationStrategy(){
		return new CustomFlywayMigrationStrategy();
	}
	
	@Bean
	public HibernateExceptionTranslator exceptionTranslation() {
		return new HibernateExceptionTranslator();
	}
}
