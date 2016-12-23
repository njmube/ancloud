package org.ancloud.fw.core.exception;


public class RestErrorMessage extends ErrorMessage {
	public RestErrorMessage(String message){
		super(message);
	}
	
	public RestErrorMessage(ErrorMessage errorMessage){
		super(errorMessage);
	}
}
