package org.ancloud.boot;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@SpringBootConfiguration
@ComponentScan(basePackages = { "org.ancloud" })
public class AncloudBootingApplication {
	
	private Logger logger = LoggerFactory.getLogger(AncloudBootingApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(AncloudBootingApplication.class, args);
	}
	
	@Bean
	@Inject
	CommandLineRunner initLucence(final EntityManager entityManager) {
		return new CommandLineRunner(){
			@Override
			public void run(String... args) throws Exception {
				try {
					FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(entityManager);
					fullTextEntityManager.createIndexer().startAndWait();
				} catch (InterruptedException e) {
					logger.error(ExceptionUtils.getMessage(e));
				}
			}};
		
	}
}
