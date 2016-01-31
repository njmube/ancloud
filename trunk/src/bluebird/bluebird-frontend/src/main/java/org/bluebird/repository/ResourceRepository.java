package org.bluebird.repository;

import org.bluebird.domain.Resource;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResourceRepository extends JpaRepository<Resource, Long>{
}
