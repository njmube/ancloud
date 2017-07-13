package org.ancloud.fw.core.helper;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.ancloud.fw.core.exception.ErrorMessage;
import org.slf4j.Logger;
import org.springframework.util.DigestUtils;

public class ExceptionHelper {

	private ExceptionHelper() {

	}

	public static ErrorMessage logException(Logger logger, Throwable throwable) {
		StringBuilder errorCodeBuilder = new StringBuilder("0x");
		String errorCode;
		ErrorMessage errorMessage = null;
		errorCodeBuilder.append(RandomStringUtils.randomNumeric(10));
		if(throwable !=null){
			String stackTrace = ExceptionUtils.getStackTrace(throwable);
			errorCodeBuilder.append("-");
			errorCodeBuilder.append(DigestUtils.md5DigestAsHex(stackTrace.getBytes()));
			errorCode = errorCodeBuilder.toString();
			if(!(logger ==null || !logger.isDebugEnabled())){
				logger.debug(errorCode);
			}
			errorMessage = new ErrorMessage(errorCode, throwable.getMessage());
			return errorMessage;
		}
		errorCode = errorCodeBuilder.toString();
		errorMessage = new ErrorMessage();
		errorMessage.setErrorCode(errorCode);
		return errorMessage;
	}
}