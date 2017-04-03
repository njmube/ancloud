package org.ancloud.repository.account;

import org.ancloud.domain.account.Account;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface AccountRepository extends AccountBaseRepository<Account> {

	Page<Account> findAll(Pageable pageable);
	
	int countByIdNotAndUserNameIgnoreCase(Long id, String userName);

	int countByIdNotAndEmail(Long id, String email);

	int countByUserNameIgnoreCase(String userName);

	int countByEmail(String email);
}
