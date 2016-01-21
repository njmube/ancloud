package org.bluebird.common.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SessionUtil {
	
	public static Object get(String objectName) {
		HttpServletRequest request = HttpServletRequestUtil.getRequest();
		if (request == null)
			return null;
		HttpSession session = request.getSession(true);
		if (session == null)
			return null;
		return session.getAttribute(objectName);
	}
	
	public static void set(String objectName, Object object) {
		HttpSession session = HttpServletRequestUtil.getRequest().getSession(true);
		if (session != null)
			session.setAttribute(objectName, object);
	}
}
