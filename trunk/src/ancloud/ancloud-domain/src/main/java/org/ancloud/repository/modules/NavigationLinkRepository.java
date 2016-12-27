package org.ancloud.repository.modules;


import java.util.List;

import org.ancloud.domain.NavigationLink;
import org.ancloud.domain.Project;
import org.springframework.data.jpa.repository.Query;

public interface NavigationLinkRepository extends BaseRepository<NavigationLink> {
	
	public List<NavigationLink> findByParentAndProject(NavigationLink parent,Project project);

	public List<NavigationLink> findByProjectOrderByItemIndex(Project project);
	
	@Query(nativeQuery=true)
	public List<NavigationLink> findByProjectAndLocaleOrderByItemIndex(Long projectId,String language,String country);

}
