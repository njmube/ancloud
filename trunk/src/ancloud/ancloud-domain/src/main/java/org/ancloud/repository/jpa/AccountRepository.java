package org.ancloud.repository.jpa;

import org.ancloud.domain.account.Account;

public interface AccountRepository extends AccountBaseRepository<Account>{
	Account findByUserName(String username);
}

