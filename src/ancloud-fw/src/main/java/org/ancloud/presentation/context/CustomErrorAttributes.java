package org.ancloud.presentation.context;

import java.util.Map;

import org.joda.time.DateTime;
import org.ancloud.fw.core.exception.ErrorMessage;
import org.ancloud.fw.core.helper.ExceptionHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.web.DefaultErrorAttributes;
import org.springframework.web.context.request.RequestAttributes;

public class CustomErrorAttributes extends DefaultErrorAttributes {
	
	private Logger logger = LoggerFactory.getLogger(CustomErrorAttributes.class);
	
	@Override
	public Map<String, Object> getErrorAttributes(RequestAttributes requestAttributes,
			boolean includeStackTrace) {
		Map<String, Object> errorAttributes = super.getErrorAttributes(requestAttributes, false);
		ErrorMessage errorMessage =  ExceptionHelper.logException(logger,super.getError(requestAttributes));
		if(errorMessage!=null){
			errorAttributes.put("errorCode", errorMessage.getErrorCode());
		}
		errorAttributes.put("timestamp", new DateTime());
		return errorAttributes;
	}
}
