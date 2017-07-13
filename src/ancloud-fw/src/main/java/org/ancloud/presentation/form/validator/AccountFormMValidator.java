package org.ancloud.presentation.form.validator;

import org.ancloud.fw.core.helper.DataTypeHelper;
import org.ancloud.presentation.form.AccountForm;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class AccountFormMValidator extends BaseAccountValidator implements Validator {
	
	@Override
	public boolean supports(Class<?> paramClass) {
		return AccountForm.class.isAssignableFrom(paramClass);
	}

	@Override
	public void validate(Object target, Errors errors) {
		//ValidationUtils.rejectIfEmpty(errors, "reenterPassword", "Confirm password must match with password.");
		if(target!=null){
			AccountForm accountForm = (AccountForm)target;
			if((StringUtils.isNotEmpty(accountForm.getPassword()) || StringUtils.isNotEmpty(accountForm.getReenterPassword())) 
					& DataTypeHelper.notEqual(accountForm.getPassword(), accountForm.getReenterPassword())) {
				errors.rejectValue("reenterPassword",null,"Confirm password must match with password.");
			}
			
			this.checkUserNameModification(accountForm, errors);
			this.checkEmailModification(accountForm, errors);
		}
	}
}
