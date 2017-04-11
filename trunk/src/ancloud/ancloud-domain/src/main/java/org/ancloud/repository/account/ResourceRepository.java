package org.ancloud.repository.account;

import org.ancloud.domain.resource.Resource;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResourceRepository extends JpaRepository<Resource, Long>{
}
