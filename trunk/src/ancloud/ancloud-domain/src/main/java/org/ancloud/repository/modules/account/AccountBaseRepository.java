package org.ancloud.repository.modules.account;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;
import org.ancloud.domain.modules.account.Account;

@NoRepositoryBean
public interface AccountBaseRepository<T extends Account> extends JpaRepository<T, Long>, JpaSpecificationExecutor<T>{

	T findByUserName(String username);
	
	T deleteByUserName(String username);

}
