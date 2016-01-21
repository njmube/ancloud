package org.bluebird.domain.repository;

import org.bluebird.domain.module.account.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long>{

	Account findByUserName(String username);
}
