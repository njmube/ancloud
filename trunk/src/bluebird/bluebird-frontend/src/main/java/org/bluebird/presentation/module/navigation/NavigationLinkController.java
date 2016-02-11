package org.bluebird.presentation.module.navigation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

@Controller
@RequestMapping(value = {"/navigation-link"})
public class NavigationLinkController {

	private RequestMappingHandlerMapping handlerMapping;

	@Autowired
	public NavigationLinkController(RequestMappingHandlerMapping handlerMapping) {
		this.handlerMapping = handlerMapping;
	}
	@ModelAttribute
	public FmNavigationLink getFmNavigationLink() {
		return new FmNavigationLink();
	}

	@RequestMapping(value = {"/","/modify"}, method = RequestMethod.GET)
	public String getHome(FmNavigationLink fmNavigationLink, Model model) {
		return "navigation-link/FmNavigationLink";
	}
}
