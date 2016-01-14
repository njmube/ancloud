package org.bluebird.domain.repository;

import javax.inject.Inject;

import org.bluebird.authentication.UserDetailsImpl;
import org.bluebird.domain.entity.Account;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class SpringAccountDetailService implements UserDetailsService {

	@Inject
	AccountRepository accountRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		Account account = accountRepository.findOne(username);
        if (account == null) {
            throw new UsernameNotFoundException(username + " is not found."); // TODO to property file
        }
        
        return new UserDetailsImpl(account);
	}

}