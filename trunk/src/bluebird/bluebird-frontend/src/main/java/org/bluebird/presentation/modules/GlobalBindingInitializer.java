package org.bluebird.presentation.modules;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.inject.Inject;

import org.bluebird.domain.common.SessionConstant;
import org.bluebird.domain.modules.account.AccountProfile;
import org.bluebird.fw.core.service.SessionService;
import org.bluebird.fw.core.util.HttpServletRequestUtil;
import org.bluebird.fw.core.util.LocaleUtils;
import org.bluebird.presentation.context.Codelist;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.support.RequestContextUtils;

@ControllerAdvice
public class GlobalBindingInitializer {

	@Inject
	SessionService sessionService;
	
	@Inject
	BeanFactory factory;
	
	@InitBinder
	public void binder(WebDataBinder binder) {
		AccountProfile accountProfile = sessionService.get(SessionConstant.SESSION_CURRENT_ACCOUNT_PROFILE,AccountProfile.class);
//		binder.registerCustomEditor(Integer.class, null, new CustomNumberEditor(Integer.class, null, true));
//		binder.registerCustomEditor(Long.class, null, new CustomNumberEditor( Long.class, null, true));
//		binder.registerCustomEditor(Boolean.class, null, new CustomBooleanEditor(true));
//		binder.registerCustomEditor(byte[].class, new ByteArrayMultipartFileEditor());
//		binder.registerCustomEditor(String.class, new StringTrimmerEditor(false));
		if(accountProfile != null){
			binder.registerCustomEditor(Date.class, new CustomDateEditor(
														new SimpleDateFormat(accountProfile.getDateFormat()),
														true));
		}
	}
	@ModelAttribute
	public void initBaseForm(Model model) {
		model.addAttribute("codelist",factory.getBean(Codelist.class));
		model.addAttribute("currentLocale",LocaleUtils.getRequestLocale());
		model.addAttribute("currentAccount",sessionService.get(SessionConstant.SESSION_ACCOUNT));
	}
}