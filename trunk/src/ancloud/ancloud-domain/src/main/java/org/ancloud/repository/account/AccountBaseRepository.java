package org.ancloud.repository.account;

import org.ancloud.domain.account.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface AccountBaseRepository<T extends Account> extends JpaRepository<T, Long>, JpaSpecificationExecutor<T>{

	T findByUserName(String username);
	
	T deleteByUserName(String username);

}
