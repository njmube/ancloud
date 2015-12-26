package org.bluebird.authentication;

import java.util.Collection;

import org.bluebird.domain.entity.Account;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UserDetailsImpl implements UserDetails{

	private static final long serialVersionUID = -7541929085537697197L;
	private Account account;

	public UserDetailsImpl(Account account) {
		this.account = account;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
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
		return this.account.isEnabled();
	}

}
