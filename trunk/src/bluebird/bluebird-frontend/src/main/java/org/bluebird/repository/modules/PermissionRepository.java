package org.bluebird.repository.modules;

import java.util.List;

import org.bluebird.domain.modules.account.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissionRepository extends JpaRepository<Permission, Long>{
}
