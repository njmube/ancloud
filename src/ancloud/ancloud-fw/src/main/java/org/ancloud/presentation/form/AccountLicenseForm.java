package org.ancloud.presentation.form;

public class AccountLicenseForm extends LicenseForm {

	private static final long serialVersionUID = 6513626865751849291L;
	private AccountForm account;
	
	public AccountForm getAccount() {
		return account;
	}

	public void setAccount(AccountForm account) {
		this.account = account;
	}
}
