package org.ancloud.presentation.form;

import org.ancloud.fw.presentation.validation.annotation.Alphanumeric;
import org.hibernate.validator.constraints.NotEmpty;

public class AccountRForm extends AccountForm {

	private static final long serialVersionUID = -7401381724579121930L;

	@Override
	@NotEmpty
	@Alphanumeric(message="Field 'userName' is not alphanumeric, can only contains [a-zA-Z0-9_]")
	public String getUserName() {
		return super.getUserName();
	}
	
	@Override
	@NotEmpty
	public String getPassword() {
		return super.getPassword();
	}
}
