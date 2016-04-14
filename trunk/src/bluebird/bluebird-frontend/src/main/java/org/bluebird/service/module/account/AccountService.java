package org.bluebird.service.module.account;

import org.bluebird.domain.module.account.Account;
import org.bluebird.domain.module.account.AccountSearchCriteria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AccountService {
	public Page<Account> findAllAccountByCriteria(AccountSearchCriteria criteria,Pageable pageable);
}
