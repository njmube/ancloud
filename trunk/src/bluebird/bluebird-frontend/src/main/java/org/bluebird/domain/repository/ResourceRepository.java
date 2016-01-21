package org.bluebird.domain.repository;

import org.bluebird.domain.entity.Resource;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResourceRepository extends JpaRepository<Resource, Long>{
}
