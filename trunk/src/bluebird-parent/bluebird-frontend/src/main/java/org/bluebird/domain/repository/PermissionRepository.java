package org.bluebird.domain.repository;

import java.util.List;

import org.bluebird.domain.entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissionRepository extends JpaRepository<Permission, String>{
}
