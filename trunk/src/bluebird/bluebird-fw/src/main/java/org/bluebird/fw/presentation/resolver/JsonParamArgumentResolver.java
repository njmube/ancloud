package org.bluebird.fw.presentation.resolver;

import org.bluebird.fw.core.util.DataTypeUtil;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

public class JsonParamArgumentResolver implements HandlerMethodArgumentResolver  {

	@Override
	public Object resolveArgument(MethodParameter methodParameter,
			ModelAndViewContainer modelandviewcontainer,
			NativeWebRequest nativeWebRequest,
			WebDataBinderFactory webdatabinderfactory) throws Exception {
		JsonParam jsonParamAnnotation = methodParameter.getParameterAnnotation(JsonParam.class);
		return DataTypeUtil.toObject(nativeWebRequest.getParameter(jsonParamAnnotation.value()), methodParameter.getNestedParameterType());
	}

	@Override
	public boolean supportsParameter(MethodParameter methodparameter) {
		return methodparameter.getParameterAnnotation(JsonParam.class) != null;
	}

}
