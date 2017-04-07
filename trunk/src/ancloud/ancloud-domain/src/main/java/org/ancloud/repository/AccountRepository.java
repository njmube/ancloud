package org.ancloud.repository;

import org.ancloud.domain.account.Account;

public interface AccountRepository extends AccountBaseRepository<Account>{
	Account findByUserName(String username);
}

