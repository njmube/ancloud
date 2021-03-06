package org.ancloud.presentation.context;

import javax.inject.Inject;

import org.ancloud.domain.account.Account;
import org.ancloud.repository.jpa.AccountRepository;
import org.ancloud.service.authentication.UserDetailsImpl;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class AccountAuditorAware implements AuditorAware<Account> {

	@Inject
	AccountRepository accountRepository;
	
	@Override
	public Account getCurrentAuditor() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		if (authentication == null || !authentication.isAuthenticated()) {
			return null;
		}
		if(authentication.getPrincipal() instanceof UserDetailsImpl) {
		  return accountRepository.findOne(((UserDetailsImpl) authentication.getPrincipal()).getAccount().getId());
		} else {
		  return accountRepository.findOne(1L);
		}
	}
}