package org.ancloud.service.navigation;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import org.ancloud.domain.NavigationLink;
import org.ancloud.domain.Project;
import org.ancloud.fw.core.util.DataTypeUtils;
import org.ancloud.repository.account.NavigationLinkRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class NavigationLinkServiceImpl implements NavigationLinkService{
	
	@Inject
	NavigationLinkRepository navigationLinkRepository;
	
	@Override
	public List<NavigationLink> findAllNavigationLinkByProject(Project project,Locale locale){
		if(locale!=null){
			return navigationLinkRepository.findByProjectAndLocaleOrderByItemIndex(project.getId(),locale.getLanguage(),locale.getCountry());
		} 
		return navigationLinkRepository.findByParentAndProject(null, project);
	}

	@Override
	public void modifyList(List<NavigationLink> navigationLinks,Project project) {
		List<NavigationLink> currentNavigationLinks = navigationLinkRepository.findByProjectOrderByItemIndex(project); 
		NavigationLink editingNavigationLink = null;
		List<Long> ids = new ArrayList<Long>();
		List<NavigationLink> newNavigationLinks = new ArrayList<NavigationLink>();
		for(NavigationLink navigationLink : navigationLinks){
			if(navigationLink.getId()!=null){
				ids.add(navigationLink.getId());
				editingNavigationLink = this.findEditingNavigationLink(navigationLink.getId(),currentNavigationLinks);
				editingNavigationLink.setMessageKey(navigationLink.getMessageKey());
				editingNavigationLink.setPath(navigationLink.getPath());
				editingNavigationLink.setGroupId(navigationLink.getGroupId());
				editingNavigationLink.setGroupIndex(navigationLink.getGroupIndex());
				editingNavigationLink.setItemIndex(navigationLink.getItemIndex());
			} else {
				newNavigationLinks.add(navigationLink);
			}
		}
		for(NavigationLink navigationLink : newNavigationLinks){
			navigationLink.setProject(project);
			navigationLink.setCode(navigationLink.getMessageKey());
			navigationLink.setParent(this.findParentNavigationLink(navigationLink.getGroupId(),newNavigationLinks,currentNavigationLinks));
		}
		navigationLinkRepository.deleteByIdNotIn(ids);
		navigationLinkRepository.save(newNavigationLinks);
	}

	private NavigationLink findEditingNavigationLink(Long id, List<NavigationLink> currentNavigationLinks) {
		for(NavigationLink navigationLink : currentNavigationLinks){
			if(DataTypeUtils.equal(navigationLink.getId(),id)){
				return navigationLink;
			}
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	private NavigationLink findParentNavigationLink(String groupId,List<NavigationLink>... navigationLinksParam) {
		for(List<NavigationLink> navigationLinks:navigationLinksParam){
			for(NavigationLink navigationLink : navigationLinks){
				if(DataTypeUtils.equal(navigationLink.getGroupIndex(),groupId)){
					return navigationLink;
				}
			}
		}
		return null;
	}
}
