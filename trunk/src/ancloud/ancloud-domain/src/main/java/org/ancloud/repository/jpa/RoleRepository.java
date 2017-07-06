package org.ancloud.repository.jpa;

import org.ancloud.domain.account.Role;
import org.ancloud.repository.BaseRepository;

public interface RoleRepository extends BaseRepository<Role>{
	public Role findOneByCodeIgnoreCase(String string);
}