package org.ancloud.repository.account;

import org.ancloud.domain.account.AccountActivity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface AccountActivityRepository extends JpaRepository<AccountActivity, Long>, JpaSpecificationExecutor<AccountActivity>{

	Page<AccountActivity> findAll(Pageable pageable);
}
