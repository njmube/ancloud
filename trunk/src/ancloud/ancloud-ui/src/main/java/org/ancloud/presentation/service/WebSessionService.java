package org.ancloud.presentation.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.ancloud.fw.core.service.SessionService;
import org.ancloud.fw.core.util.DataTypeUtil;
import org.ancloud.fw.presentation.util.HttpServletRequestUtil;
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
		Object result = session.getAttribute(objectName);
		if(logger.isTraceEnabled()){
			logger.trace(String.format("Get %s from session succesfully!", objectName));
			logger.trace(DataTypeUtil.toJson(result));
		}
		return result;
	}
	
	@Override
	public Object put(String objectName, Object object) {
		HttpSession session = HttpServletRequestUtil.getRequest().getSession(true);
		if (session != null){
			session.setAttribute(objectName, object);
			if(logger.isTraceEnabled()){
				logger.trace(String.format("Set %s to session succesfully!", objectName));
				logger.trace(DataTypeUtil.toJson(object));
			}
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

	@Override
	public Map<Object, List<Object>> getAllSessions() {
		// TODO Auto-generated method stub
		return null;
	}
}
