package org.ancloud.presentation.modules.navigation;

import java.util.Locale;

import javax.inject.Inject;
import javax.validation.Valid;

import org.ancloud.domain.NavigationLink;
import org.ancloud.domain.Project;
import org.ancloud.domain.utils.SessionConstant;
import org.ancloud.fw.core.service.SessionService;
import org.ancloud.fw.presentation.BaseController;
import org.ancloud.fw.presentation.resolver.Session;
import org.ancloud.service.navigation.NavigationLinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
	SessionService sessionService;
	
	@Inject
	NavigationLinkService navigationLinkService;
	
	
	@ModelAttribute
	public FmNavigationLink getFmNavigationLink() {
		return new FmNavigationLink();
	}

	@RequestMapping(value = {"","/modify"}, method = RequestMethod.GET)
	public String displayModify(FmNavigationLink fmNavigationLink, Model model,@Session(key=SessionConstant.SESSION_CURRENT_PROJECT) Project project,@Session(key=SessionConstant.SESSION_CURRENT_LOCALE) Locale locale) {
		fmNavigationLink.setNavigationLinks(this.mapper.mapAsList(navigationLinkService.findAllNavigationLinkByProject(project,locale), FmiNavigationLink.class));
		
		return "navigation-link/FmNavigationLink";
	}
	
	@RequestMapping(value = {"/modify"}, method = RequestMethod.POST)
	public String processModify(@Valid FmNavigationLink fmNavigationLink,BindingResult bindingResult, Model model,@Session(key=SessionConstant.SESSION_CURRENT_PROJECT) Project project) {
		String dest = null;
		
		if(bindingResult.hasErrors()){
			dest = "navigation-link/FmNavigationLink";
		} else {
			navigationLinkService.modifyList(this.mapper.mapAsList(fmNavigationLink.getNavigationLinks(), NavigationLink.class),project);
//			TODO sessionService.put(SessionConstant.SESSION_NAVIGATION_LINKS, navigationLinkService.findAllNavigationLinkByProject(project, null));
			dest = "redirect:/navigation-link/modify";
		}
		
		return dest;
	}
}
