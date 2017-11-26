package org.ancloud.repository.jpa;

import java.util.List;

import org.ancloud.domain.resource.Resource;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResourceRepository extends JpaRepository<Resource, Long>{
  public List<Resource> findAllByCategory(String category);
}
