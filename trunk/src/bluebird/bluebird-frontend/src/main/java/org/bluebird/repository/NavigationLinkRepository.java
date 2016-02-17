package org.bluebird.repository;


import java.util.List;

import org.bluebird.domain.NavigationLink;
import org.bluebird.domain.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface NavigationLinkRepository extends JpaRepository<NavigationLink, Long> {
	public List<NavigationLink> findByParentAndProject(NavigationLink parent,Project project);

	public List<NavigationLink> findByProject(Project project);

	@Modifying
	@Query("DELETE FROM #{#entityName} A WHERE A.id NOT IN ?1")
	public Integer deleteByIdIn(Iterable<Long> navigationLinkIds);
}
