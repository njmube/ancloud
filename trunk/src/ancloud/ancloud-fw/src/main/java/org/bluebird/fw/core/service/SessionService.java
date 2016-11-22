package org.bluebird.fw.core.service;



public interface SessionService{
	
	public Object get(String objectName);
	
	public Object put(String objectName, Object object);
	
	public<T> T get(String objectName,Class<T> clazz);
}
