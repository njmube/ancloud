package org.ancloud.presentation.context;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.ancloud.domain.modules.account.Account;
import org.ancloud.service.authentication.UserDetailsImpl;

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