package org.ancloud.wapi.form;

import org.ancloud.domain.modules.account.enums.AccountStatus;

public class AccountStatusForm {
	private String userName;
	
	private AccountStatus accountStatus;
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public AccountStatus getAccountStatus() {
		return accountStatus;
	}

	public void setAccountStatus(AccountStatus accountStatus) {
		this.accountStatus = accountStatus;
	}
}
