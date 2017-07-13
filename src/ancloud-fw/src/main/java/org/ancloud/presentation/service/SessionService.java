package org.ancloud.presentation.service;

import java.util.List;
import java.util.Map;

import org.springframework.session.Session;

public interface SessionService{
	
	public Object get(String objectName);
	
	public Object put(String objectName, Object object);
	
	public<T> T get(String objectName,Class<T> clazz);

	Map<Object, List<Object>> getAllSessions();

	Map<String, ? extends Session> findByIndexNameAndIndexValue(String principalNameIndexName, String userName);
	
	public Map<String, ? extends Session> getSessionsByUserName(String userName);
	
	public String getUserAgent();
}
