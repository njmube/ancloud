package org.ancloud.fw.core.exception;

public class BusinessException extends RuntimeException{

	private static final long serialVersionUID = -1067855554063468673L;
	
	
	public BusinessException(String... messages){
		super(String.join("\r\n", messages));
	}

 
}
