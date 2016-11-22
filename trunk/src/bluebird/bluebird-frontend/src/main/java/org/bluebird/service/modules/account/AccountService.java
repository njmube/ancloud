package org.bluebird.service.modules.account;

import org.bluebird.domain.modules.account.Account;
import org.bluebird.domain.modules.account.AccountSearchCriteria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AccountService {
	public Page<Account> findAllAccountByCriteria(AccountSearchCriteria criteria,Pageable pageable);
}
