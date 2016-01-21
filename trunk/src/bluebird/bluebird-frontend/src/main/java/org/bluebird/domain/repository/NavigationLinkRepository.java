package org.bluebird.domain.repository;


import java.util.List;

import org.bluebird.domain.entity.NavigationLink;
import org.bluebird.domain.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NavigationLinkRepository extends JpaRepository<NavigationLink, Long> {
	public List<NavigationLink> findByParentAndProject(NavigationLink parent,Project project);
}
