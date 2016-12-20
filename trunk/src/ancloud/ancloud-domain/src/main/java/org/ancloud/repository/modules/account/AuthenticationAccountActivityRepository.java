package org.ancloud.repository.modules.account;

import org.ancloud.domain.modules.account.AuthenticationAccountActivity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface AuthenticationAccountActivityRepository extends JpaRepository<AuthenticationAccountActivity, Long>, JpaSpecificationExecutor<AuthenticationAccountActivity>{

	Page<AuthenticationAccountActivity> findAll(Pageable pageable);
}
