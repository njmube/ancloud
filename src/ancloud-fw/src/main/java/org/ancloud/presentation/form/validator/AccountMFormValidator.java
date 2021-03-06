package org.ancloud.presentation.form.validator;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.ancloud.fw.core.helper.DataTypeHelper;
import org.ancloud.presentation.form.AccountForm;
import org.ancloud.presentation.form.AccountMForm;
import org.ancloud.repository.jpa.AccountRepository;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class AccountMFormValidator extends BaseAccountValidator implements Validator {
	
	@Override
	public boolean supports(Class<?> paramClass) {
		return AccountMForm.class.isAssignableFrom(paramClass);
	}

	@Override
	public void validate(Object target, Errors errors) {
		//ValidationUtils.rejectIfEmpty(errors, "reenterPassword", "Confirm password must match with password.");
		if(target!=null){
			AccountMForm accountForm = (AccountMForm)target;
			if((StringUtils.isNotEmpty(accountForm.getPassword()) || StringUtils.isNotEmpty(accountForm.getReenterPassword())) 
					& DataTypeHelper.notEqual(accountForm.getPassword(), accountForm.getReenterPassword())) {
				errors.rejectValue("reenterPassword",null,"Confirm password must match with password.");
			}
			
			this.checkUserNameModification(accountForm, errors);
			this.checkEmailModification(accountForm, errors);
		}
	}
}
