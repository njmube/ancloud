package org.bluebird.presentation.modules.navigation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

public class FmNavigationLink {
	
	@Valid
	private List<FmiNavigationLink> navigationLinks = new ArrayList<FmiNavigationLink>();

	public List<FmiNavigationLink> getNavigationLinks() {
		return navigationLinks;
	}

	public void setNavigationLinks(List<FmiNavigationLink> navigationLinks) {
		this.navigationLinks = navigationLinks;
	}
}
