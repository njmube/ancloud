package org.ancloud.presentation.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.ancloud.fw.core.service.SessionService;
import org.ancloud.fw.presentation.helper.HttpServletRequestHelpers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.session.Session;
import org.springframework.stereotype.Component;

@Component("sessionService")
public class WebSessionService implements SessionService {
	
	Logger logger = LoggerFactory.getLogger(WebSessionService.class);
	
	@Inject
	SessionRegistry sessionRegistry;
	
	public Object get(String objectName) {
		HttpServletRequest request = HttpServletRequestHelpers.getRequest();
		if (request == null)
			return null;
		HttpSession session = request.getSession(true);
		if (session == null)
			return null;
		Object result = session.getAttribute(objectName);
		if(logger.isTraceEnabled()){
			logger.trace(String.format("Get %s from session succesfully!", objectName));
		}
		return result;
	}
	
	@Override
	public Object put(String objectName, Object object) {
		HttpSession session = HttpServletRequestHelpers.getRequest().getSession(true);
		if (session != null){
			session.setAttribute(objectName, object);
			if(logger.isTraceEnabled()){
				logger.trace(String.format("Set %s to session succesfully!", objectName));
			}
		}
		return object;
	}
	
	@Override
	public String getUserAgent() {
		String userAgent=null;
		try{
			userAgent = (String) HttpServletRequestHelpers.getRequest().getAttribute("User-Agent");
		} catch(Exception ex){
			if(logger.isDebugEnabled()){
				logger.debug("Cannot fetch user-agent.",ex);
			}
		}
		return userAgent;
	}
	
	@Override
	public<T> T get(String objectName,Class<T> clazz) {
		T value = null;
		try{
			value = clazz.cast(get(objectName));
		} catch(Exception ex){
			logger.error("Generic cast fail", ex);
		}
		return value;
	}

	@Override
	public Map<Object, List<Object>> getAllSessions() {
		Map<Object,  List<Object>> sessionMap = new HashMap<Object, List<Object>>();
		for(Object principal:sessionRegistry.getAllPrincipals()){
			sessionMap.put(principal, (List<Object>)(List<?>) sessionRegistry.getAllSessions(principal, true));
		}
		return sessionMap;
	}

	@Override
	public Map<String, ? extends Session> findByIndexNameAndIndexValue(String principalNameIndexName, String userName) {
		// TODO Auto-generated method stub
		return null;
	}
}
