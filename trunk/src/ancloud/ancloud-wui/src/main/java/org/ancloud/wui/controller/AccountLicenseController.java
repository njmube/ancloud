package org.ancloud.wui.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.ancloud.domain.account.AccountLicense;
import org.ancloud.domain.account.AccountLicenseSearchCriteria;
import org.ancloud.domain.account.License;
import org.ancloud.fw.core.exception.BusinessException;
import org.ancloud.fw.core.service.SessionService;
import org.ancloud.fw.presentation.BaseController;
import org.ancloud.fw.presentation.message.ResultMessages;
import org.ancloud.presentation.form.AccountLicenseForm;
import org.ancloud.presentation.form.AccountLicenseMForm;
import org.ancloud.presentation.form.AccountLicenseRForm;
import org.ancloud.presentation.form.AccountLicenseRFormValidator;
import org.ancloud.service.account.AccountLicenseService;
import org.ancloud.wui.form.AccountLicenseSearchForm;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping(value="admin/account-license")
@SessionAttributes(types={AccountLicenseSearchForm.class})
public class AccountLicenseController extends BaseController {
	
	@Inject
	AccountLicenseService accountLicenseService;
	
	@Inject
	SessionService sessionService;
	
	@Inject
	AccountLicenseRFormValidator accountLicenseRFormValidator;
	
	@InitBinder(value={"accountLicenseRForm"})
	public void initaccountForm(WebDataBinder binder){
		binder.addValidators(accountLicenseRFormValidator);
	}
	
	@RequestMapping(value={"/show-info"}, method = RequestMethod.GET)
	public String showLicenseInfo(AccountLicenseForm licenseForm,Model model){
		License license = null;
		try {
			license = accountLicenseService.findById(licenseForm.getId());
		} catch(Exception ex){
			model.addAttribute("messages",
					ResultMessages.error().add(ex.getMessage()));
			ex.printStackTrace();
			return "account-license/FrAccountLicense";
		}
		model.addAttribute("license", mapper.map(license, AccountLicenseForm.class));
		return "account-license/FvAccountLicense";
	}
	@RequestMapping(value={"/search"}, method = {RequestMethod.GET})
	public String displaySearch(AccountLicenseSearchForm accountLicenseSearchForm, Model model, @PageableDefault Pageable pageable,HttpServletRequest req){
		AccountLicenseSearchCriteria criteria = mapper.map(accountLicenseSearchForm, AccountLicenseSearchCriteria.class);
		Page<AccountLicense> page = accountLicenseService.findAllByCriteria(criteria, pageable);
		model.addAttribute("page",page);
		return "account-license/FsAccountLicense";
	}
	
	@RequestMapping(value="/search", method = {RequestMethod.POST})
	public String processSearch(AccountLicenseSearchForm accountLicenseSearchForm, Model model, @PageableDefault Pageable pageable){
		AccountLicenseSearchCriteria criteria = mapper.map(accountLicenseSearchForm, AccountLicenseSearchCriteria.class);
		Page<AccountLicense> page = accountLicenseService.findAllByCriteria(criteria, pageable);
		model.addAttribute("page",page);
		return "account-license/FsAccountLicense";
	} 
	
	@RequestMapping(value={"/register"}, method = {RequestMethod.GET})
	public String displayRegister(AccountLicenseRForm accountLicenseRForm, Model mode){
		return "account-license/FrAccountLicense";
	}
	
	@RequestMapping(value={"/register"}, method = {RequestMethod.POST})
	public String processRegister(@Valid AccountLicenseRForm accountLicenseRForm,BindingResult bindingResult, Model model,RedirectAttributes redirectAttributes, @PageableDefault Pageable pageable){
		if(bindingResult.hasErrors()){
			return "account-license/FrAccountLicense";
		}
		try{
			accountLicenseService.register(mapper.map(accountLicenseRForm, AccountLicense.class));
		} catch(Exception ex){
			ex.printStackTrace();
			model.addAttribute("messages",
					ResultMessages.error().add(ex.getMessage()));
			return "account-license/FrAccountLicense";
		}
		redirectAttributes.addFlashAttribute("messages",ResultMessages.success().add("Registration's successfull"));
		return "redirect:/admin/account-license/search?sort=createdDate,desc";
	}
	
	@RequestMapping(value={"/modify"}, method = {RequestMethod.GET})
	public String displayModify(AccountLicenseMForm accountLicenseMForm, Model model, @PageableDefault Pageable pageable){
		AccountLicense license = accountLicenseService.findById(accountLicenseMForm.getId());
		model.addAttribute("accountLicenseMForm", mapper.map(license, AccountLicenseMForm.class));
		return "account-license/FmAccountLicense";
	}
	
	@RequestMapping(value={"/modify"}, method = {RequestMethod.POST})
	public String processModify(@Valid AccountLicenseForm accountLicenseMForm,BindingResult bindingResult,RedirectAttributes redirectAttributes, Model model, @PageableDefault Pageable pageable){
		if(bindingResult.hasErrors()){
			return "account-license/FmAccountLicense";
		}
		try{
			accountLicenseService.modify(mapper.map(accountLicenseMForm, AccountLicense.class));
		} catch(BusinessException ex){
			model.addAttribute("messages", ResultMessages.error().add(ex.getMessage()));
			return "account-license/FmAccountLicense";
		}
		redirectAttributes.addFlashAttribute("messages",ResultMessages.success().add("Registration's successfull"));
		return "redirect:/admin/license/search?sort=lastUpdatedDate,desc";
	}
	@RequestMapping(value={"/delete"}, method = {RequestMethod.POST,RequestMethod.GET})
	public String processDeletion(AccountLicenseMForm accountLicenseMForm,BindingResult bindingResult,RedirectAttributes redirectAttributes, Model model){
		try{
			AccountLicense accountLicense = mapper.map(accountLicenseMForm, AccountLicense.class);
			accountLicenseService.delete(accountLicense.getId());
		} catch(BusinessException ex){
			redirectAttributes.addFlashAttribute("messages",ResultMessages.error().add(ex.getMessage()));
			return "redirect:/admin/account-license/search";
		}
		redirectAttributes.addFlashAttribute("messages",ResultMessages.success().add("Deletion''s successfull"));
		return "redirect:/admin/account-license/search";
	}
	@RequestMapping(value={"/getQrCode"}, method = {RequestMethod.GET})
	public String getQrCode(AccountLicenseRForm accountLicenseRForm, Model mode){
		String file = accountLicenseService.getQrCodeFile(mapper.map(accountLicenseRForm, AccountLicense.class));
		return "redirect:/resources/qr/"+file;
	}
}
