package org.ancloud.presentation.context;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.ancloud.domain.utils.SystemConstant;
import org.ancloud.presentation.message.InitializableMessageSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ApplicationContextEvent;
import org.springframework.context.event.ContextRefreshedEvent;

public class ApplicationContextListener implements ApplicationListener<ApplicationContextEvent> {

	public Logger logger = LoggerFactory.getLogger(ApplicationContextListener.class);
	@Override
	public void onApplicationEvent(ApplicationContextEvent arg0) {
		if(arg0 instanceof ContextRefreshedEvent){
			ApplicationContext context = arg0.getApplicationContext();
			InitializableMessageSource messageSource = context.getBean(InitializableMessageSource.class);
			if(messageSource != null)
				messageSource.initialize();
			
			try {
				this.initializeSystemFiles();
			} catch (IOException e) {
				if(logger.isErrorEnabled()){
					logger.error("Cannot initialize system files.",e);
				}
			}
		}
	}

	private void initializeSystemFiles() throws IOException {
		Path homePath = Paths.get(SystemConstant.PATH_HOME);
		if(!Files.exists(homePath)){
			Files.createDirectory(homePath);
		}
		Path qrDirPath = Paths.get(SystemConstant.PATH_QR_CODE);
		if(!Files.exists(qrDirPath)){
			Files.createDirectory(qrDirPath);
		}
	}

}
