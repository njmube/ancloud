package org.ancloud.repository.account;


import java.util.List;

import org.ancloud.domain.NavigationLink;
import org.ancloud.domain.Project;
import org.ancloud.repository.BaseModelRepository;
import org.springframework.data.jpa.repository.Query;

public interface NavigationLinkRepository extends BaseModelRepository<NavigationLink> {
	
	public List<NavigationLink> findByParentAndProject(NavigationLink parent,Project project);

	public List<NavigationLink> findByProjectOrderByItemIndex(Project project);
	
	@Query(nativeQuery=true)
	public List<NavigationLink> findByProjectAndLocaleOrderByItemIndex(Long projectId,String language,String country);

}
