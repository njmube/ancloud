package org.ancloud.fw.core.serviceimpl;

import java.util.Collection;

import org.ancloud.domain.modules.account.Account;
import org.ancloud.domain.modules.account.enums.AccountStatus;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UserDetailsImpl implements UserDetails{

	private static final long serialVersionUID = -7541929085537697197L;
	private Account account;
	private Collection<? extends GrantedAuthority> authorities;
	

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
		return this.account.isAccountNonExpired();
	}

	@Override
	public boolean isAccountNonLocked() {
		return this.account.isAccountNonLocked();
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return this.account.isCredentialsNonExpired();
	}

	@Override
	public boolean isEnabled() {
		return this.account.getAccountStatus()==AccountStatus.Enabled;
	}

}
