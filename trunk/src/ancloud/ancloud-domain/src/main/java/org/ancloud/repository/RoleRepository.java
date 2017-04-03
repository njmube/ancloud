package org.ancloud.repository;

import org.ancloud.domain.account.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long>{

	public Role findOneByCodeIgnoreCase(String string);
}

