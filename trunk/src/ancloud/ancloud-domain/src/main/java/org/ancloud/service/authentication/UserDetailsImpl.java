package org.ancloud.service.authentication;

import java.util.Collection;
import java.util.Date;

import org.ancloud.domain.account.Account;
import org.ancloud.domain.account.AccountLicense;
import org.ancloud.domain.account.enums.AccountStatus;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UserDetailsImpl implements UserDetails{

	private static final long serialVersionUID = -7541929085537697197L;
	private Account account;
	private Collection<? extends GrantedAuthority> authorities;
	private Date lastPasswordResetDate;
	private AccountLicense license;
	

	public Account getAccount() {
		return account;
	}

	public UserDetailsImpl(Account account,Collection<? extends GrantedAuthority> authorities) {
		this.account = account;
		this.authorities = authorities;
	}

	public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
		this.authorities = authorities;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return this.account.getPassword();
	}

	@Override
	public String getUsername() {
		return this.account.getUserName();
	}

	@Override
	public boolean isAccountNonExpired() {
		if(license == null || license.getToDate()==null) return true;
		if(license.getFromDate()==null) return license.getToDate().isAfterNow();
		return license.getFromDate().isBeforeNow() & license.getToDate().isAfterNow();
	}

	@Override
	public boolean isAccountNonLocked() {
		if(license==null) {
			return this.account.isAccountNonLocked();
		}
		return license.isNonLocked();
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return this.account.isCredentialsNonExpired();
	}

	@Override
	public boolean isEnabled() {
		return this.account.getAccountStatus()==AccountStatus.Enabled;
	}

	public Date getLastPasswordResetDate() {
		return lastPasswordResetDate;
	}

	public void setLastPasswordResetDate(Date lastPasswordResetDate) {
		this.lastPasswordResetDate = lastPasswordResetDate;
	}

	public AccountLicense getLicense() {
		return license;
	}

	public void setLicense(AccountLicense license) {
		this.license = license;
	}

}
