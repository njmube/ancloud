package org.bluebird.service.module.navigation;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.bluebird.domain.NavigationLink;
import org.bluebird.domain.Project;
import org.bluebird.repository.NavigationLinkRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class NavigationLinkServiceImpl implements NavigationLinkService{
	
	@Inject
	NavigationLinkRepository navigationLinkRepository;
	
	@Override
	public List<NavigationLink> findAllNavigationLinkByProject(Project project){
		return navigationLinkRepository.findByProject(project);
	}

	@Override
	public void modifyList(List<NavigationLink> navigationLinks) {
		List<Long> ids = new ArrayList<Long>();
		for(NavigationLink navigationLink : navigationLinks){
			if(navigationLink.getId()!=null){
				ids.add(navigationLink.getId());
			}
		}
		navigationLinkRepository.deleteByIdIn(ids);
		navigationLinkRepository.save(navigationLinks);
	}
}
