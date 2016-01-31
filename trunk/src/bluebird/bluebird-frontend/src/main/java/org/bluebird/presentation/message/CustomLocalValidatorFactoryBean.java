package org.bluebird.presentation.message;

import javax.validation.ParameterNameProvider;

import org.springframework.context.MessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.validation.beanvalidation.MessageSourceResourceBundleLocator;

public class CustomLocalValidatorFactoryBean extends LocalValidatorFactoryBean {
	@Override
	public ParameterNameProvider getParameterNameProvider() {
		// TODO Auto-generated method stub
		return null;
	}


//	@SuppressWarnings("rawtypes")
//	@Override
//	protected void processConstraintViolations(
//			Set<ConstraintViolation<Object>> violations, Errors errors) {
//		// TODO Auto-generated method stub
//		if (violations.size() > 1) {
//			Class classValidation = violations.iterator().next()
//					.getRootBeanClass();
//			Field[] arrField = classValidation.getDeclaredFields();
//			Set<ConstraintViolation<Object>> violationsTemp = new LinkedHashSet<ConstraintViolation<Object>>();
//			for (int i = 0; i < arrField.length; i++) {
//				for (ConstraintViolation<Object> constraintViolation : violations) {
//					if (getPath(
//							constraintViolation.getPropertyPath().toString())
//							.equals(arrField[i].getName())) {
//						if (!violationsTemp.contains(constraintViolation))
//							violationsTemp.add(constraintViolation);
//					}
//				}
//			}
//			if (violationsTemp.size() == violations.size())
//				super.processConstraintViolations(violationsTemp, errors);
//			else
//				super.processConstraintViolations(violations, errors);
//		} else {
//			super.processConstraintViolations(violations, errors);
//		}
//
//	}

//	private String getPath(String propertyPath) {
//		int index = propertyPath.indexOf('[');
//		if (index > 0) {
//			return propertyPath.substring(0, index);
//		} else
//			return propertyPath;
//	}

	@Override
	public void setValidationMessageSource(MessageSource messageSource) {
		super.setMessageInterpolator(new BbMessageInterpolator(new MessageSourceResourceBundleLocator(messageSource)));
	}
}
