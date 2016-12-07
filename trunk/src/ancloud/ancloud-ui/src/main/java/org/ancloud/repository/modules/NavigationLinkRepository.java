package org.ancloud.repository.modules;


import java.util.List;
import java.util.Locale;

import org.ancloud.domain.NavigationLink;
import org.ancloud.domain.Project;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface NavigationLinkRepository extends CommonRepository<NavigationLink> {
	
	public List<NavigationLink> findByParentAndProject(NavigationLink parent,Project project);

	public List<NavigationLink> findByProjectOrderByItemIndex(Project project);
	
	@Query(nativeQuery=true)
	public List<NavigationLink> findByProjectAndLocaleOrderByItemIndex(Long projectId,String language,String country);

}
