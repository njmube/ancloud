package org.ancloud.presentation.context;

import org.ancloud.domain.account.Account;
import org.ancloud.service.authentication.UserDetailsImpl;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class AccountAuditorAware implements AuditorAware<Account> {

	@Override
	public Account getCurrentAuditor() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		if (authentication == null || !authentication.isAuthenticated()) {
			return null;
		}
		return ((UserDetailsImpl) authentication.getPrincipal()).getAccount();
	}
}