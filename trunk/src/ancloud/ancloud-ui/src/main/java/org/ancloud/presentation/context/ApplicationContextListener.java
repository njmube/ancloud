package org.ancloud.presentation.context;

import org.ancloud.presentation.message.InitializableMessageSource;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ApplicationContextEvent;
import org.springframework.context.event.ContextRefreshedEvent;

public class ApplicationContextListener implements ApplicationListener<ApplicationContextEvent> {

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
