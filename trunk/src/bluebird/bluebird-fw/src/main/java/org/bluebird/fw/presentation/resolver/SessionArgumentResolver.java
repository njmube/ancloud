package org.bluebird.fw.presentation.resolver;

import javax.inject.Inject;

import org.bluebird.fw.core.service.SessionService;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

public class SessionArgumentResolver implements HandlerMethodArgumentResolver  {

	@Inject
	SessionService sessionService;
	
	@Override
	public Object resolveArgument(MethodParameter methodParameter,
			ModelAndViewContainer modelandviewcontainer,
			NativeWebRequest nativeWebRequest,
			WebDataBinderFactory webdatabinderfactory) throws Exception {
		Session sessionAnnotation = methodParameter.getParameterAnnotation(Session.class);
		Object resolvedObject = sessionService.get(sessionAnnotation.key(),methodParameter.getDeclaringClass());
		return resolvedObject;
	}

	@Override
	public boolean supportsParameter(MethodParameter methodparameter) {
		return methodparameter.getParameterAnnotation(Session.class) != null;
	}

}
