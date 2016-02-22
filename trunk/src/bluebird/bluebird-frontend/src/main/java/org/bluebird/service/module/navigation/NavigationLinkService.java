package org.bluebird.service.module.navigation;

import java.util.List;

import org.bluebird.domain.NavigationLink;
import org.bluebird.domain.Project;

public interface NavigationLinkService {
	public List<NavigationLink> findAllNavigationLinkByProject(Project project);

	public void modifyList(List<NavigationLink> navigationLinks,Project project);

}
