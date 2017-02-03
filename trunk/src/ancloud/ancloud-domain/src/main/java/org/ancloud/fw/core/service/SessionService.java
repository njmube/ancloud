package org.ancloud.fw.core.service;

import java.util.List;
import java.util.Map;

import org.springframework.session.Session;

public interface SessionService{
	
	public Object get(String objectName);
	
	public Object put(String objectName, Object object);
	
	public<T> T get(String objectName,Class<T> clazz);

	Map<Object, List<Object>> getAllSessions();
}
