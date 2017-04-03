package org.medtech.wui.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.medtech.domain.modules.account.AccountProfile;
import org.medtech.domain.utils.SessionConstant;
import org.medtech.fw.core.service.SessionService;
import org.medtech.fw.presentation.util.LocaleUtils;
import org.medtech.presentation.context.Codelist;
import org.medtech.presentation.form.AccountFormValidator;

@ControllerAdvice
public class GlobalBindingInitializer extends ResponseEntityExceptionHandler {

	private Logger logger = LoggerFactory.getLogger(GlobalBindingInitializer.class);
	
	@Inject
	SessionService sessionService;
	
	@Inject
	BeanFactory factory;
	
	@Inject
	AccountFormValidator accountFormValidator;
	
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