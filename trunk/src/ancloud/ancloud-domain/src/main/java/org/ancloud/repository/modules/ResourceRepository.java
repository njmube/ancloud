package org.ancloud.repository.modules;

import org.ancloud.domain.Resource;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResourceRepository extends JpaRepository<Resource, Long>{
}
