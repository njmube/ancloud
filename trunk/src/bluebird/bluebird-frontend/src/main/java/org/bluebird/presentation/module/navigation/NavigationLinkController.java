package org.bluebird.presentation.module.navigation;

import javax.inject.Inject;
import javax.validation.Valid;

import org.bluebird.domain.NavigationLink;
import org.bluebird.domain.Project;
import org.bluebird.fw.presentation.BaseController;
import org.bluebird.fw.presentation.resolver.Session;
import org.bluebird.service.module.navigation.NavigationLinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

@Controller
@RequestMapping(value = {"/navigation-link"})
public class NavigationLinkController extends BaseController {

	private RequestMappingHandlerMapping handlerMapping;

	@Autowired
	public NavigationLinkController(RequestMappingHandlerMapping handlerMapping) {
		this.handlerMapping = handlerMapping;
	}
	
	@Inject
	NavigationLinkService navigationLinkService;
	
	
	@ModelAttribute
	public FmNavigationLink getFmNavigationLink() {
		return new FmNavigationLink();
	}

	@RequestMapping(value = {"","/modify"}, method = RequestMethod.GET)
	public String displayModify(FmNavigationLink fmNavigationLink, Model model,@Session(key="project") Project project) {
		fmNavigationLink.setNavigationLinks(this.mapper.mapAsList(navigationLinkService.findAllNavigationLinkByProject(project), FmiNavigationLink.class));
		
		return "navigation-link/FmNavigationLink";
	}
	
	@RequestMapping(value = {"/modify"}, method = RequestMethod.POST)
	public String processModify(@Valid FmNavigationLink fmNavigationLink,BindingResult bindingResult, Model model,@Session(key="project") Project project) {
		String dest = null;
		
		if(bindingResult.hasErrors()){
			dest = "navigation-link/FmNavigationLink";
		} else {
			navigationLinkService.modifyList(this.mapper.mapAsList(fmNavigationLink.getNavigationLinks(), NavigationLink.class),project);
			dest = "redirect:/navigation-link/modify";
		}
		
		return dest;
	}
}
