package org.ancloud.fw.core.exception;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ErrorMessage {
	
	private String errorCode;
	
	private List<String> errors;

	public ErrorMessage() {
	}
	
	public ErrorMessage(ErrorMessage errorMessage) {
		this.errors=errorMessage.getErrors();
	}

	public ErrorMessage(List<String> errors) {
		this.errors = errors;
	}

	public ErrorMessage(String error) {
		this(Collections.singletonList(error));
	}

	public ErrorMessage(String errorCode,String... errors) {
		this(Arrays.asList(errors));
		this.errorCode=errorCode;
	}

	public List<String> getErrors() {
		return errors;
	}

	public void setErrors(List<String> errors) {
		this.errors = errors;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
}
