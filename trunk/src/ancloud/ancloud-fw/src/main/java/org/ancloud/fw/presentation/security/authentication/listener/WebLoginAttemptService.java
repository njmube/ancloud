package org.ancloud.fw.presentation.security.authentication.listener;

import java.util.HashMap;
import java.util.Map;

import org.ancloud.service.account.LoginAttemptService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class WebLoginAttemptService implements LoginAttemptService{

	private Logger logger = LoggerFactory.getLogger(WebLoginAttemptService.class);
	private final int MAX_ATTEMPT = 10;
	private Map<String, Integer> attemptsCache;

	public WebLoginAttemptService() {
		super();
		attemptsCache = new HashMap<String,Integer>();
	}

	public void loginSucceeded(String key) {
		attemptsCache.remove(key);
	}

	public void loginFailed(String key) {
		Integer attempts = attemptsCache.get(key);
		if(attempts==null){
			attempts = 0 ;
		}
		attempts++;
		attemptsCache.put(key, attempts);
	}

	public boolean isBlocked(String key) {
		Integer attempts = attemptsCache.get(key);
		return !(attempts == null || attemptsCache.get(key) < MAX_ATTEMPT);
	}
	
	@Scheduled(cron = "${org.ancloud.authentication.attempts.cleanup.cron.expression:0 30/15 * * * *}")
	public void cleanupAttemps() {
		// TODO Clean login attemps.
		logger.debug("/Clean login attemps.");
	}
}