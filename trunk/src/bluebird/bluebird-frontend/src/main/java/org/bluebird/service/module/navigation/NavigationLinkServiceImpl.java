package org.bluebird.service.module.navigation;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.bluebird.domain.NavigationLink;
import org.bluebird.domain.Project;
import org.bluebird.fw.core.util.DataTypeUtil;
import org.bluebird.repository.NavigationLinkRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

@Service
@Transactional
public class NavigationLinkServiceImpl implements NavigationLinkService{
	
	@Inject
	NavigationLinkRepository navigationLinkRepository;
	
	@Override
	public List<NavigationLink> findAllNavigationLinkByProject(Project project){
		return navigationLinkRepository.findByProjectOrderByItemIndex(project);
	}

	@Override
	public void modifyList(List<NavigationLink> navigationLinks,Project project) {
		List<NavigationLink> currentNavigationLinks = this.findAllNavigationLinkByProject(project); 
		NavigationLink editingNavigationLink = null;
		List<Long> ids = new ArrayList<Long>();
		List<NavigationLink> newNavigationLinks = new ArrayList<NavigationLink>();
		for(NavigationLink navigationLink : navigationLinks){
			if(navigationLink.getId()!=null){
				ids.add(navigationLink.getId());
				editingNavigationLink = this.findEditingNavigationLink(navigationLink.getId(),currentNavigationLinks);
				editingNavigationLink.setMessageCode(navigationLink.getMessageCode());
				editingNavigationLink.setPath(navigationLink.getPath());
				editingNavigationLink.setGroupId(navigationLink.getGroupId());
				editingNavigationLink.setGroupIndex(navigationLink.getGroupIndex());
				editingNavigationLink.setItemIndex(navigationLink.getItemIndex());
			} else {
				newNavigationLinks.add(navigationLink);
			}
		}
		for(NavigationLink navigationLink : newNavigationLinks){
			navigationLink.setCode(navigationLink.getMessageCode());
			navigationLink.setParent(this.findParentNavigationLink(navigationLink.getGroupId(),newNavigationLinks,currentNavigationLinks));
		}
		navigationLinkRepository.deleteByIdNotIn(ids);
		navigationLinkRepository.save(newNavigationLinks);
	}

	private NavigationLink findEditingNavigationLink(Long id, List<NavigationLink> currentNavigationLinks) {
		for(NavigationLink navigationLink : currentNavigationLinks){
			if(DataTypeUtil.equal(navigationLink.getId(),id)){
				return navigationLink;
			}
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	private NavigationLink findParentNavigationLink(String groupId,List<NavigationLink>... navigationLinksParam) {
		for(List<NavigationLink> navigationLinks:navigationLinksParam){
			for(NavigationLink navigationLink : navigationLinks){
				if(DataTypeUtil.equal(navigationLink.getGroupIndex(),groupId)){
					return navigationLink;
				}
			}
		}
		return null;
	}
}
