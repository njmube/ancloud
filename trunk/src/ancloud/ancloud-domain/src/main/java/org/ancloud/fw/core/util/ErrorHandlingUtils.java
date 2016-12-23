package org.ancloud.fw.core.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;

import org.apache.commons.collections.ListUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.ObjectError;
import org.ancloud.fw.core.exception.ErrorMessage;

public class ErrorHandlingUtils {

	private ErrorHandlingUtils() {

	}

	public static ErrorMessage logException(Logger logger, Exception ex) {
		String stackTrace = ExceptionUtils.getStackTrace(ex);
		String errorCode = DigestUtils.md5DigestAsHex(stackTrace.getBytes());
		// String errorCode = "0x".concat(RandomStringUtils.randomNumeric(5));
		logger.error(errorCode, stackTrace);
		return new ErrorMessage(errorCode, ex.getMessage());
	}

	public static ErrorMessage logValidationError(Logger logger, List<ObjectError> allErrors) {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		try {
			ObjectOutputStream oos = new ObjectOutputStream(bos);
			oos.writeObject(allErrors);
		} catch (IOException ex) {
			if(logger.isDebugEnabled()){
				logger.debug("Cannot deserilaize List<ObjectError> to bytes array.",ExceptionUtils.getStackTrace(ex));
			}
		}
		String errorCode = DigestUtils.md5DigestAsHex(bos.toByteArray());
		String message = StringUtils.collectionToDelimitedString(allErrors, "\r\n");
		
		logger.error(errorCode, message);
		return new ErrorMessage(errorCode, message);
	}

}