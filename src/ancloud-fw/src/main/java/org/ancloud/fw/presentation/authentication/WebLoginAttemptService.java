package org.ancloud.fw.presentation.authentication;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.ancloud.service.account.LoginAttemptService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

@Component
public class WebLoginAttemptService implements LoginAttemptService{

  private Logger logger = LoggerFactory.getLogger(WebLoginAttemptService.class);
  private final int MAX_ATTEMPT = 3;
  private Cache<String, Integer> cacheMap;

  public WebLoginAttemptService() {
    super();
    cacheMap = CacheBuilder.newBuilder()
                        .concurrencyLevel(8)
                        .maximumSize(10000)
                        .expireAfterAccess(100, TimeUnit.SECONDS)
                        .<String,Integer>build();
}

  public void loginSucceeded(String key,String sessionId) {
    cacheMap.asMap().remove(key);
  }

  public void loginFailed(String key,String sessionId) {
    Integer attempts = cacheMap.getIfPresent(key);
    if(attempts==null){
      attempts = 0 ;
    }
    attempts++;
    cacheMap.put(key, attempts);
  }

  public boolean isBlocked(String key) {
    boolean isBlocked=false;
    
    Integer attempts = cacheMap.getIfPresent(key);
    isBlocked = !(attempts == null || attempts < MAX_ATTEMPT);
    
    return isBlocked;
  }
  
  @Scheduled(cron = "${org.ancloud.authentication.attempts.cleanup.cron.expression:0 30/15 * * * *}")
  public void cleanupAttemps() {
    // TODO Clean login attemps.
    logger.debug("/Clean login attemps.");
  }
}