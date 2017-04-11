package org.ancloud.tienganhvui.controller;

import javax.inject.Inject;
import javax.validation.Valid;

import org.ancloud.domain.account.Account;
import org.ancloud.domain.account.AccountSearchCriteria;
import org.ancloud.domain.account.enums.AccountStatus;
import org.ancloud.domain.constant.SystemConstant;
import org.ancloud.fw.core.exception.BusinessException;
import org.ancloud.fw.core.service.SessionService;
import org.ancloud.fw.presentation.BaseController;
import org.ancloud.fw.presentation.message.ResultMessages;
import org.ancloud.presentation.form.AccountForm;
import org.ancloud.presentation.form.AccountMForm;
import org.ancloud.presentation.form.AccountRForm;
import org.ancloud.presentation.form.validator.AccountMFormValidator;
import org.ancloud.presentation.form.validator.AccountRFormValidator;
import org.ancloud.service.account.AccountService;
import org.ancloud.tienganhvui.form.AccountSearchForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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
@RequestMapping(value="admin/account")
@SessionAttributes(types={AccountSearchForm.class})
public class AccountController extends BaseController {
	
	@Inject
	AccountService accountService;
	
	@Inject
	SessionService sessionService;
	
	@Inject
	AccountRFormValidator accountRFormValidator;
	
	@Inject
	AccountMFormValidator accountMFormValidator;
	
	@InitBinder(value={"accountMForm"})
	public void initAccountMForm(WebDataBinder binder){
		binder.addValidators(accountMFormValidator);
	}
	
	@InitBinder(value={"accountRForm"})
	public void initAccountRForm(WebDataBinder binder){
		binder.addValidators(accountRFormValidator);
	}
	
	@RequestMapping(value={"/show-info"}, method = RequestMethod.GET)
	public String showAccountInfo(AccountForm accountForm,Model model,RedirectAttributes redirectAttributes){
		Account account;
		try{
			account = accountService.findByIdAndAccountType(accountForm.getId(),accountForm.getAccountType());
		} catch(BusinessException ex){
			redirectAttributes.addFlashAttribute(SystemConstant.VIEW_MESSAGES, ResultMessages.error().add(ex.getMessage()));
			return "redirect:/admin/account/search";
		}
		model.addAttribute("account", mapper.map(account, AccountForm.class));
		return "account/FiAccountInfo";
	}
	
	@RequestMapping(value={"","/search"}, method = {RequestMethod.GET})
	public String displaySearch(AccountSearchForm accountSearchForm, Model model, @PageableDefault Pageable pageable){
		AccountSearchCriteria criteria = mapper.map(accountSearchForm, AccountSearchCriteria.class);
		Page<Account> page = accountService.findAllByCriteria(criteria, pageable);
		model.addAttribute("page",page);
		return "account/FsAccount";
	}
	
	@RequestMapping(value="/search", method = {RequestMethod.POST})
	public String processSearch(AccountSearchForm accountSearchForm, Model model, @PageableDefault Pageable pageable){
		AccountSearchCriteria criteria = mapper.map(accountSearchForm, AccountSearchCriteria.class);
		Page<Account> page = accountService.findAllByCriteria(criteria, pageable);
		model.addAttribute("page",page);
		return "account/FsAccount";
	} 
	
	@RequestMapping(value={"","/register"}, method = {RequestMethod.GET})
	public String displayRegister(AccountRForm accountRForm, Model model, @PageableDefault Pageable pageable){
		
		return "account/FrAccount";
	}
	
	@RequestMapping(value={"/register"}, method = {RequestMethod.POST})
	public String processRegister(@Valid AccountRForm accountRForm,BindingResult bindingResult, Model model,RedirectAttributes redirectAttributes, @PageableDefault Pageable pageable){
		if(bindingResult.hasErrors()){
			return "account/FrAccount";
		}
		try{
			switch(accountRForm.getAccountType()){
//			case Doctor:
//				Doctor doctor = mapper.map(accountRForm, Doctor.class);
//				accountService.<Doctor>registerNew(doctor);
//				break;
			default:
				Account account = mapper.map(accountRForm, Account.class);
				accountService.<Account>registerNew(account);
				break;
			}
		} catch(Exception ex){
			model.addAttribute(SystemConstant.VIEW_MESSAGES,
					ResultMessages.error().add(ex.getMessage()));
			ex.printStackTrace();
			return "account/FrAccount";
		}
		redirectAttributes.addFlashAttribute(SystemConstant.VIEW_MESSAGES,ResultMessages.success().add("Registration's successfull"));
		return "redirect:/admin/account/search?sort=createdDate,desc";
	}
	
	@RequestMapping(value={"/modify"}, method = {RequestMethod.GET})
	public String displayModify(AccountMForm accountMForm, Model model, @PageableDefault Pageable pageable){
		Account account = accountService.findByIdAndAccountType(accountMForm.getId(),accountMForm.getAccountType());
		model.addAttribute("accountMForm", mapper.map(account, AccountMForm.class));
		return "account/FmAccount";
	}
	
	@RequestMapping(value={"/modify"}, method = {RequestMethod.POST})
	public String processModify(@Valid AccountMForm accountMForm,BindingResult bindingResult,RedirectAttributes redirectAttributes, Model model, @PageableDefault Pageable pageable){
		if(bindingResult.hasErrors()){
			return "account/FmAccount";
		}
		try{
			switch(accountMForm.getAccountType()){
//			case Doctor:
//				Doctor doctor = mapper.map(accountMForm, Doctor.class);
//				accountService.<Doctor>modify(doctor);
//				break;
			default:
				Account account = mapper.map(accountMForm, Account.class);
				accountService.modify(account);
				break;
			
			}
		} catch(BusinessException ex){
			model.addAttribute(SystemConstant.VIEW_MESSAGES, ResultMessages.error().add(ex.getMessage()));
			return "account/FmAccount";
		}
		redirectAttributes.addFlashAttribute(SystemConstant.VIEW_MESSAGES,ResultMessages.success().add("Modification''s successfull"));
		return "redirect:/admin/account/search?sort=lastUpdatedDate,desc";
	}
	@RequestMapping(value={"/delete"}, method = {RequestMethod.POST,RequestMethod.GET})
	public String processDeletion(AccountMForm accountMForm,BindingResult bindingResult,RedirectAttributes redirectAttributes, Model model){
		try{
			Account account = mapper.map(accountMForm, Account.class);
			accountService.delete(account);
		} catch(BusinessException ex){
			redirectAttributes.addFlashAttribute(SystemConstant.VIEW_MESSAGES,ResultMessages.error().add(ex.getMessage()));
			return "redirect:/admin/account/search";
		}
		redirectAttributes.addFlashAttribute(SystemConstant.VIEW_MESSAGES,ResultMessages.success().add("Deletion''s successfull"));
		return "redirect:/admin/account/search?sort=lastUpdatedDate,desc";
	}
	@RequestMapping(value={"","/approve"}, method = {RequestMethod.GET,RequestMethod.POST})
	public String approve(AccountForm accountForm, Model model,RedirectAttributes redirectAttributes){
		Account account = mapper.map(accountForm, Account.class);
		try{
			if(accountForm.getIsApproval()==1){
				accountService.approveAccount(account,AccountStatus.Enabled);
			} else {
				accountService.approveAccount(account,AccountStatus.Disabled);
			}
		} catch(Exception ex){
			redirectAttributes.addFlashAttribute(SystemConstant.VIEW_MESSAGES,ResultMessages.error().add(ex.getMessage()));
			return "redirect:/admin/account/search";
		}
		
		redirectAttributes.addFlashAttribute(SystemConstant.VIEW_MESSAGES,ResultMessages.success().add("Approval's successfull"));
		return "redirect:/admin/account/search";
	}
}
