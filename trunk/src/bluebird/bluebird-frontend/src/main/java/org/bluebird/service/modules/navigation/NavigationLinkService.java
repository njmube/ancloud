package org.bluebird.service.modules.navigation;

import java.util.List;
import java.util.Locale;

import org.bluebird.domain.NavigationLink;
import org.bluebird.domain.Project;

public interface NavigationLinkService {
	public List<NavigationLink> findAllNavigationLinkByProject(Project project,Locale locale);

	public void modifyList(List<NavigationLink> navigationLinks,Project project);

}
