package org.ancloud.presentation.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.session.ExpiringSession;
import org.springframework.session.FindByIndexNameSessionRepository;
import org.springframework.session.Session;
import org.springframework.session.SessionRepository;

public class RedisSessionService extends WebSessionService {

	Logger logger = LoggerFactory.getLogger(RedisSessionService.class);

	private static final String REDIS_KEY_PREFIX = "spring:session:index:"+FindByIndexNameSessionRepository.PRINCIPAL_NAME_INDEX_NAME+":";

	@Inject
	@Qualifier("sessionRedisTemplate")
	private RedisOperations<Object, Object> redisOps;

	@Inject
	private SessionRepository sessionRepository;
	
	@Override
	public Map<Object,List<Object>> getAllSessions(){
		Map<Object, List<Object>> sessionMap = new HashMap<Object,List<Object>>();
		Set<String> userKeySet = (Set<String>)(Set<?>)redisOps.keys(REDIS_KEY_PREFIX + "*");

		for (String userKey : userKeySet) {
			Set<String> values = (Set<String>)(Set<?>)redisOps.opsForSet().members(userKey);
			for(String key: values){
				String id = key;
				ExpiringSession session = (ExpiringSession) sessionRepository.getSession(id);
//				SecurityContext securityContext = (SecurityContext)session.getAttribute("SPRING_SECURITY_CONTEXT");
				
//				Object principal = securityContext.getAuthentication().getPrincipal();
				Object principal = userKey.substring(REDIS_KEY_PREFIX.length());
				if(sessionMap.containsKey(principal)){
					sessionMap.get(principal).add(session);
				} else {
					List<Object> sessions = new ArrayList<Object>();
					sessions.add(session);
					sessionMap.put(principal, sessions);
				}
			}
		}
		return sessionMap;
		
	}
	
	@Override
	public Map<String, ? extends Session> findByIndexNameAndIndexValue(String principalNameIndexName, String userName) {
		if(sessionRepository instanceof FindByIndexNameSessionRepository) {
		return ((FindByIndexNameSessionRepository)sessionRepository).findByIndexNameAndIndexValue(FindByIndexNameSessionRepository.PRINCIPAL_NAME_INDEX_NAME, userName);
		}
		return super.findByIndexNameAndIndexValue(principalNameIndexName, userName);
	}
}
