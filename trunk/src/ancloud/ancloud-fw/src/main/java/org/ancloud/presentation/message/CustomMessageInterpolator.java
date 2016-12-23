package org.ancloud.presentation.message;

import java.util.Locale;

import javax.validation.MessageInterpolator;

import org.ancloud.fw.presentation.util.LocaleUtils;


public class CustomMessageInterpolator implements MessageInterpolator{

	
	public CustomMessageInterpolator(){
	}
	
	public String interpolate(String message, Context context) {
		String interpolatedMessage = message;
		interpolatedMessage = interpolateMessage( message, context, LocaleUtils.getCurrentLocale());
		return interpolatedMessage;
	}

	public String interpolate(String message, Context context, Locale locale) {
		String interpolatedMessage = message;
		interpolatedMessage = interpolateMessage( message, context, locale );
		return interpolatedMessage;
	}
	
	private String interpolateMessage(String message, Context context,Locale locale) {
		String resolveMessage = message;
		return resolveMessage;
	}
}
