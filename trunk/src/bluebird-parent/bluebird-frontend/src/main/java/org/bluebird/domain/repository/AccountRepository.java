package org.bluebird.domain.repository;

import org.bluebird.domain.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long>{

	Account findByUserName(String username);
}
