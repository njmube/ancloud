package org.ancloud.presentation.form.validator;

import org.ancloud.presentation.form.ProfileAccountForm;
import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.Errors;

public class ProfileAccountValidator extends BaseAccountValidator {
	protected void checkFirstName(ProfileAccountForm profileAccountForm,Errors errors) {
		if((StringUtils.isEmpty(profileAccountForm.getFirstName()))) {
			errors.rejectValue("firstName",null,"First name is required.");
		}
	}
	protected void checkLastName(ProfileAccountForm profileAccountForm,Errors errors) {
		if((StringUtils.isEmpty(profileAccountForm.getLastName()))) {
			errors.rejectValue("lastName",null,"Last name is required.");
		}
	}
	protected void checkBirthday(ProfileAccountForm profileAccountForm,Errors errors) {
		if((profileAccountForm.getBirthday()==null)) {
			errors.rejectValue("birthday",null,"Birthday is required.");
		}
	}
	protected void checkCommonProfileAccount(ProfileAccountForm profileAccountForm, Errors errors) {
		this.checkFirstName(profileAccountForm, errors);
		this.checkLastName(profileAccountForm, errors);
		this.checkBirthday(profileAccountForm, errors);
		
	}
	
}
