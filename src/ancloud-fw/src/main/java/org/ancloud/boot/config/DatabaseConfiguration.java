package org.ancloud.boot.config;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.hibernate5.HibernateExceptionTranslator;

@Configuration
@EntityScan("org.ancloud.domain")
@EnableJpaRepositories(basePackages = { "org.ancloud.repository" })
public class DatabaseConfiguration {

	@Bean
	public HibernateExceptionTranslator exceptionTranslation() {
		return new HibernateExceptionTranslator();
	}
}
