package org.ancloud.presentation.context;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.ancloud.presentation.message.InitializableMessageSource;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ApplicationContextEvent;
import org.springframework.context.event.ContextRefreshedEvent;

public class ApplicationContextListener implements ApplicationListener<ApplicationContextEvent> {

	private Logger logger = LoggerFactory.getLogger(ApplicationContextListener.class);
	
	@Override
	public void onApplicationEvent(ApplicationContextEvent arg0) {
		if(arg0 instanceof ContextRefreshedEvent){
			ApplicationContext context = arg0.getApplicationContext();
			InitializableMessageSource messageSource = context.getBean(InitializableMessageSource.class);
			if(messageSource != null)
				messageSource.initialize();
		}
		
	}

}
