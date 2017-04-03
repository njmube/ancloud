package org.medtech.wui.form;

import org.medtech.domain.modules.account.enums.AccountStatus;

public class AccountSearchForm {
	
	
	private String userName;
	
	private String name;
	
	private String email;

	private AccountStatus accountStatus = null;
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public AccountStatus getAccountStatus() {
		return accountStatus;
	}

	public void setAccountStatus(AccountStatus status) {
		this.accountStatus = status;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	
}
