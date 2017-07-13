package org.ancloud.wui;

import javax.persistence.EntityManagerFactory;

import org.ancloud.service.authentication.AuthenticationSuccessHandler;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootConfiguration
@EnableAutoConfiguration
@ComponentScan(basePackages="org.ancloud")
@EnableJpaRepositories(basePackages = { "org.ancloud.repository" })
@EntityScan(basePackages = { "org.ancloud.domain" })
@CrossOrigin(origins = "*", maxAge = 3600)
@EnableScheduling
@EnableAspectJAutoProxy
public class AppConfig {
	
	@Bean
	public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(emf);
		return transactionManager;
	}
	@Bean
	public AuthenticationSuccessHandler successHandler(){
		AuthenticationSuccessHandler successHandler = new AuthenticationSuccessHandler();
		successHandler.setDefaultTargetUrl("/account/search");
		return successHandler;
	}
}
