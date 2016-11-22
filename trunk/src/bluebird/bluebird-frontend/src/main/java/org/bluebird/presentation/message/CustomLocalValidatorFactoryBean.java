package org.bluebird.presentation.message;

import org.springframework.context.MessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.validation.beanvalidation.MessageSourceResourceBundleLocator;

public class CustomLocalValidatorFactoryBean extends LocalValidatorFactoryBean {

	@Override
	public void setValidationMessageSource(MessageSource messageSource) {
		super.setMessageInterpolator(new CustomMessageInterpolator(new MessageSourceResourceBundleLocator(messageSource)));
	}
}
