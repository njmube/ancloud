package org.ancloud.repository.account;

import org.ancloud.domain.account.Account;

public interface AccountRepository extends AccountBaseRepository<Account>{
	Account findByUserName(String username);
}

