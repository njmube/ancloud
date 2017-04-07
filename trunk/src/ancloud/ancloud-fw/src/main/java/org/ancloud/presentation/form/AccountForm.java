package org.ancloud.presentation.form;


import org.ancloud.domain.account.enums.AccountStatus;
import org.ancloud.domain.account.enums.AccountType;
import org.hibernate.validator.constraints.Length;
import org.joda.time.DateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class AccountForm extends BaseForm {

	private static final long serialVersionUID = -1900019761440463396L;

	@Length(max=128)
	private String userName;
	
	private String title;
	
	@Length(max=254)
	private String email;
	
	private String contactNumber;

	@Length(max=128)
	private String password;
	
	private DateTime birthday;
	
	private String reenterPassword;

	@JsonIgnore
	private boolean accountNonExpired;

	@JsonIgnore
	private boolean accountNonLocked;

	@JsonIgnore
	private boolean credentialsNonExpired;

	private AccountStatus accountStatus;
	
	private AccountType accountType;
	
	@JsonIgnore
	private AccountForm approver;

	private int isApproval;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getReenterPassword() {
		return reenterPassword;
	}

	public void setReenterPassword(String reenterPassword) {
		this.reenterPassword = reenterPassword;
	}

	public boolean isAccountNonExpired() {
		return accountNonExpired;
	}

	public void setAccountNonExpired(boolean accountNonExpired) {
		this.accountNonExpired = accountNonExpired;
	}

	public boolean isAccountNonLocked() {
		return accountNonLocked;
	}

	public void setAccountNonLocked(boolean accountNonLocked) {
		this.accountNonLocked = accountNonLocked;
	}

	public boolean isCredentialsNonExpired() {
		return credentialsNonExpired;
	}

	public void setCredentialsNonExpired(boolean credentialsNonExpired) {
		this.credentialsNonExpired = credentialsNonExpired;
	}

	public AccountStatus getAccountStatus() {
		return accountStatus;
	}

	public void setAccountStatus(AccountStatus status) {
		this.accountStatus = status;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public AccountForm getApprover() {
		return approver;
	}

	public void setApprover(AccountForm approver) {
		this.approver = approver;
	}

	public AccountType getAccountType() {
		return accountType;
	}

	public void setAccountType(AccountType accountType) {
		this.accountType = accountType;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public DateTime getBirthday() {
		return birthday;
	}

	public void setBirthday(DateTime birthday) {
		this.birthday = birthday;
	}

	public int getIsApproval() {
		return isApproval;
	}
	public void setIsApproval(int isApproval) {
		this.isApproval = isApproval;
	}
}
