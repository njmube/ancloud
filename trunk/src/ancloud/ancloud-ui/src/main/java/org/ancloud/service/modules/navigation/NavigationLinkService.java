package org.ancloud.service.modules.navigation;

import java.util.List;
import java.util.Locale;

import org.ancloud.domain.NavigationLink;
import org.ancloud.domain.Project;

public interface NavigationLinkService {
	public List<NavigationLink> findAllNavigationLinkByProject(Project project,Locale locale);

	public void modifyList(List<NavigationLink> navigationLinks,Project project);

}
