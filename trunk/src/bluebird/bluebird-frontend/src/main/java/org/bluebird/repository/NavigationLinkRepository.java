package org.bluebird.repository;


import java.util.List;

import org.bluebird.domain.NavigationLink;
import org.bluebird.domain.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NavigationLinkRepository extends JpaRepository<NavigationLink, Long> {
	public List<NavigationLink> findByParentAndProject(NavigationLink parent,Project project);
}
