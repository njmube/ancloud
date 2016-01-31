package org.bluebird.presentation.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.bluebird.fw.core.service.SessionService;
import org.bluebird.presentation.util.HttpServletRequestUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class WebSessionService implements SessionService {
	
	Logger logger = LoggerFactory.getLogger(SessionService.class);
	
	public Object get(String objectName) {
		HttpServletRequest request = HttpServletRequestUtil.getRequest();
		if (request == null)
			return null;
		HttpSession session = request.getSession(true);
		if (session == null)
			return null;
		logger.info(String.format("Get %s from session succesfully!", objectName));
		return session.getAttribute(objectName);
	}
	
	@Override
	public Object put(String objectName, Object object) {
		HttpSession session = HttpServletRequestUtil.getRequest().getSession(true);
		if (session != null){
			session.setAttribute(objectName, object);
			logger.info(String.format("Set %s to session succesfully!", objectName));
		}
		return object;
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
}
