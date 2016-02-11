package org.bluebird.presentation.module.navigation;

import java.util.ArrayList;
import java.util.List;

public class FmNavigationLink {
	
	private List<FmiNavigationLink> navigationLinks = new ArrayList<FmiNavigationLink>();

	public List<FmiNavigationLink> getNavigationLinks() {
		return navigationLinks;
	}

	public void setNavigationLinks(List<FmiNavigationLink> navigationLinks) {
		this.navigationLinks = navigationLinks;
	}
}
