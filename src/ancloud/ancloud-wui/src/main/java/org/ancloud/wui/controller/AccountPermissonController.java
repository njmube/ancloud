package org.ancloud.wui.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.ancloud.domain.account.AccountPermission;
import org.ancloud.domain.account.AccountPermissionSearchCriteria;
import org.ancloud.domain.constant.SystemConstant;
import org.ancloud.fw.core.exception.BusinessException;
import org.ancloud.fw.presentation.BaseController;
import org.ancloud.fw.presentation.message.ResultMessages;
import org.ancloud.presentation.form.AccountPermissionForm;
import org.ancloud.presentation.service.SessionService;
import org.ancloud.service.account.AccountPermissionService;
import org.ancloud.wui.form.AccountPermissionSearchForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping(value="admin/account-permission")
@SessionAttributes(types={AccountPermissionSearchForm.class})
public class AccountPermissonController extends BaseController {
	
	@Inject
	AccountPermissionService accountPermissionService;
	
	@Inject
	SessionService sessionService;
	
	@RequestMapping(value={"/search"}, method = {RequestMethod.GET})
	public String displaySearch(AccountPermissionSearchForm accountPermissionSearchForm, Model model, @PageableDefault Pageable pageable,HttpServletRequest req){
		AccountPermissionSearchCriteria criteria = mapper.map(accountPermissionSearchForm, AccountPermissionSearchCriteria.class);
		Page<AccountPermission> page = accountPermissionService.findAllByCriteria(criteria, pageable);
		model.addAttribute(SystemConstant.BEAN_NAME_PAGING,page);
		return "account-permission/FsAccountPermission";
	}
	
	@RequestMapping(value="/search", method = {RequestMethod.POST})
	public String processSearch(AccountPermissionSearchForm accountPermissionSearchForm, Model model, @PageableDefault Pageable pageable){
		AccountPermissionSearchCriteria criteria = mapper.map(accountPermissionSearchForm, AccountPermissionSearchCriteria.class);
		Page<AccountPermission> page = accountPermissionService.findAllByCriteria(criteria, pageable);
		model.addAttribute(SystemConstant.BEAN_NAME_PAGING,page);
		return "account-permission/FsAccountPermission";
	} 
	
	@RequestMapping(value={"/register"}, method = {RequestMethod.GET})
	public String displayRegister(AccountPermissionForm accountPermissionRForm, Model mode){
		return "account-permission/FrAccountPermission";
	}
	
	@RequestMapping(value={"/register"}, method = {RequestMethod.POST})
	public String processRegister(@Valid AccountPermissionForm accountPermissionRForm,BindingResult bindingResult, Model model,RedirectAttributes redirectAttributes, @PageableDefault Pageable pageable){
		if(bindingResult.hasErrors()){
			return "account-permission/FrAccountPermission";
		}
		try{
			accountPermissionService.register(mapper.map(accountPermissionRForm, AccountPermission.class));
		} catch(Exception ex){
			ex.printStackTrace();
			model.addAttribute("messages",
					ResultMessages.error().add(ex.getMessage()));
			return "account-permission/FrAccountPermission";
		}
		redirectAttributes.addFlashAttribute("messages",ResultMessages.success().add("Registration's successfull"));
		return "redirect:/admin/account-permission/search?sort=createdDate,desc";
	}
	
	@RequestMapping(value={"/modify"}, method = {RequestMethod.GET})
	public String displayModify(AccountPermissionForm accountPermissionMForm, Model model, @PageableDefault Pageable pageable){
		AccountPermission permission = accountPermissionService.findById(accountPermissionMForm.getId());
		model.addAttribute("permissionMForm", mapper.map(permission, AccountPermissionForm.class));
		return "account-permission/FmAccountPermission";
	}
	
	@RequestMapping(value={"/modify"}, method = {RequestMethod.POST})
	public String processModify(@Valid AccountPermissionForm accountPermissionForm,BindingResult bindingResult,RedirectAttributes redirectAttributes, Model model, @PageableDefault Pageable pageable){
		if(bindingResult.hasErrors()){
			return "account-permission/FmAccountPermission";
		}
		try{
			accountPermissionService.modify(mapper.map(accountPermissionForm, AccountPermission.class));
		} catch(BusinessException ex){
			model.addAttribute("messages", ResultMessages.error().add(ex.getMessage()));
			return "account-permission/FmAccountPermission";
		}
		redirectAttributes.addFlashAttribute("messages",ResultMessages.success().add("Registration's successfull"));
		return "redirect:/admin/permission/search?sort=lastUpdatedDate,desc";
	}
	@RequestMapping(value={"/delete"}, method = {RequestMethod.POST,RequestMethod.GET})
	public String processDeletion(AccountPermissionForm accountPermissionForm,BindingResult bindingResult,RedirectAttributes redirectAttributes, Model model){
		try{
			AccountPermission accountPermission = mapper.map(accountPermissionForm, AccountPermission.class);
			accountPermissionService.delete(accountPermission);
		} catch(BusinessException ex){
			redirectAttributes.addFlashAttribute("messages",ResultMessages.error().add(ex.getMessage()));
			return "redirect:/admin/account-permission/search";
		}
		redirectAttributes.addFlashAttribute("messages",ResultMessages.success().add("Deletion''s successfull"));
		return "redirect:/admin/account-permission/search";
	}
}
