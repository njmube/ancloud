package org.bluebird.service.account;

import javax.inject.Inject;

import org.bluebird.authentication.UserDetailsImpl;
import org.bluebird.domain.entity.Account;
import org.bluebird.domain.repository.AccountRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

	@Inject
	AccountRepository accountRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Account account = accountRepository.findByUserName(username);
		if (account == null) {
			throw new UsernameNotFoundException(username + " is not found."); 
		}
		return new UserDetailsImpl(account);
	}

}