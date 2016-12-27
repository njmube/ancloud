package org.ancloud.repository.modules;

import org.springframework.data.jpa.repository.JpaRepository;
import org.ancloud.domain.modules.account.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{

	public Role findOneByCodeIgnoreCase(String string);
}

