package org.ancloud.fw.presentation.validation;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.ancloud.fw.core.exception.ErrorMessage;
import org.slf4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.ObjectError;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class ValidationResponseEntityBuilder {

	public static ResponseEntity<?> build(Logger logger, List<ObjectError> allErrors) {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		try {
			ObjectOutputStream oos = new ObjectOutputStream(bos);
			oos.writeObject(allErrors);
		} catch (IOException ex) {
			if(logger.isDebugEnabled()){
				logger.debug("Cannot deserilaize List<ObjectError> to bytes array.",ex);
			}
		}
		String errorCode = DigestUtils.md5DigestAsHex(bos.toByteArray());
		String message = StringUtils.collectionToDelimitedString(allErrors, "\r\n");
		
		Map<String, Object> errorAttributes = new LinkedHashMap<String, Object>();
		errorAttributes.put("timestamp", new Date());
		errorAttributes.put("status", 401);
		errorAttributes.put("error", message);
		
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		String path = request.getRequestURI();
		if (path != null) {
			errorAttributes.put("path", path);
		}
		if(logger.isDebugEnabled()){
			logger.debug(errorCode, message);
		}
		return ResponseEntity.badRequest().body(errorAttributes);
	}

}