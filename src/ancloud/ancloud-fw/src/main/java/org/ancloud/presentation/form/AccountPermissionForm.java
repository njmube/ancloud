package org.ancloud.presentation.form;

public class AccountPermissionForm extends BaseForm {

	private static final long serialVersionUID = -4367723819633861909L;
	
	private AccountForm account;
	
	private PermissionForm permission;
	
	public AccountForm getAccount() {
		return account;
	}

	public void setAccount(AccountForm account) {
		this.account = account;
	}

	public PermissionForm getPermission() {
		return permission;
	}

	public void setPermission(PermissionForm permission) {
		this.permission = permission;
	}
}
