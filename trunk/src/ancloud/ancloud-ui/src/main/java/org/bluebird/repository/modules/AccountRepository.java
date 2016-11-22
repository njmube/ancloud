package org.bluebird.repository.modules;

import org.bluebird.domain.Project;
import org.bluebird.domain.modules.account.Account;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface AccountRepository extends JpaRepository<Account, Long>, JpaSpecificationExecutor<Account>{

	Account findByUserName(String username);

	Page<Account> findAllByProject(Project project, Pageable pageable);
}
