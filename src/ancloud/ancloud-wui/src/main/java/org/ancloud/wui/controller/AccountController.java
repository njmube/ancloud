package org.ancloud.wui.controller;

import javax.inject.Inject;
import javax.validation.Valid;

import org.ancloud.domain.account.Account;
import org.ancloud.domain.account.AccountSearchCriteria;
import org.ancloud.domain.account.enums.AccountStatus;
import org.ancloud.domain.constant.SystemConstant;
import org.ancloud.fw.core.exception.BusinessException;
import org.ancloud.fw.presentation.BaseController;
import org.ancloud.fw.presentation.message.ResultMessages;
import org.ancloud.presentation.form.AccountForm;
import org.ancloud.presentation.form.validator.AccountFormMValidator;
import org.ancloud.presentation.form.validator.AccountFormRValidator;
import org.ancloud.presentation.service.SessionService;
import org.ancloud.service.account.AccountService;
import org.ancloud.wui.form.AccountSearchForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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
@RequestMapping(value="admin/account")
@SessionAttributes(types={AccountSearchForm.class})
public class AccountController extends BaseController {
	
	@Inject
	AccountService<Account> accountService;
	
	@Inject
	SessionService sessionService;
	
	@Inject
	AccountFormRValidator accountRFormValidator;
	
	@Inject
	AccountFormMValidator accountFormValidator;
	
	@InitBinder(value={"accountForm"})
	public void initAccountForm(WebDataBinder binder){
		binder.addValidators(accountFormValidator);
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
			redirectAttributes.addFlashAttribute(SystemConstant.BEAN_NAME_MESSAGES, ResultMessages.error().add(ex.getMessage()));
			return "redirect:/admin/account/search";
		}
		model.addAttribute("account", mapper.map(account, AccountForm.class));
		return "account/FiAccountInfo";
	}
	
	@RequestMapping(value={"","/search"}, method = {RequestMethod.GET})
	public String displaySearch(AccountSearchForm accountSearchForm, Model model, @PageableDefault Pageable pageable){
		AccountSearchCriteria criteria = mapper.map(accountSearchForm, AccountSearchCriteria.class);
		Page<Account> page = accountService.findAllByCriteria(criteria, pageable);
		model.addAttribute(SystemConstant.BEAN_NAME_PAGING,page);
		return "account/FsAccount";
	}
	
	@RequestMapping(value="/search", method = {RequestMethod.POST})
	public String processSearch(AccountSearchForm accountSearchForm, Model model, @PageableDefault Pageable pageable){
		AccountSearchCriteria criteria = mapper.map(accountSearchForm, AccountSearchCriteria.class);
		Page<Account> page = accountService.findAllByCriteria(criteria, pageable);
		model.addAttribute(SystemConstant.BEAN_NAME_PAGING,page);
		return "account/FsAccount";
	} 
	
	@RequestMapping(value="/search-ajax", method = {RequestMethod.GET})
	public ResponseEntity<?> processAjaxSearch(AccountSearchForm accountSearchForm, Model model, @PageableDefault Pageable pageable){
		AccountSearchCriteria criteria = mapper.map(accountSearchForm, AccountSearchCriteria.class);
		Page<Account> page = accountService.findAllByCriteria(criteria, pageable);
		return ResponseEntity.ok(page);
	} 
	
	@RequestMapping(value={"","/register"}, method = {RequestMethod.GET})
	public String displayRegister(AccountForm accountForm, Model model){
		
		return "account/FrAccount";
	}
	
	@RequestMapping(value={"/register"}, method = {RequestMethod.POST})
	public String processRegister(@Valid AccountForm accountForm,BindingResult bindingResult, Model model,RedirectAttributes redirectAttributes, @PageableDefault Pageable pageable){
		accountRFormValidator.validate(accountForm, bindingResult);
		if(bindingResult.hasErrors()){
			return "account/FrAccount";
		}
		try{
			switch(accountForm.getAccountType()){
			default:
				Account account = mapper.map(accountForm, Account.class);
				accountService.register(account);
				break;
			}
		} catch(Exception ex){
			model.addAttribute(SystemConstant.BEAN_NAME_MESSAGES,
					ResultMessages.error().add(ex.getMessage()));
			ex.printStackTrace();
			return "account/FrAccount";
		}
		redirectAttributes.addFlashAttribute(SystemConstant.BEAN_NAME_MESSAGES,ResultMessages.success().add("Registration's successfull"));
		return "redirect:/admin/account/search?sort=createdDate,desc";
	}
	
	@RequestMapping(value={"/modify"}, method = {RequestMethod.GET})
	public String displayModify(AccountForm accountForm, Model model, @PageableDefault Pageable pageable){
		Account account = accountService.findByIdAndAccountType(accountForm.getId(),accountForm.getAccountType());
		model.addAttribute("accountForm", mapper.map(account, AccountForm.class));
		return "account/FmAccount";
	}
	
	@RequestMapping(value={"/modify"}, method = {RequestMethod.POST})
	public String processModify(@Valid AccountForm accountForm,BindingResult bindingResult,RedirectAttributes redirectAttributes, Model model, @PageableDefault Pageable pageable){
		this.accountFormValidator.validate(accountForm, bindingResult);
		if(bindingResult.hasErrors()){
			return "account/FmAccount";
		}
		try{
			switch(accountForm.getAccountType()){
			default:
				Account account = mapper.map(accountForm, Account.class);
				accountService.modify(account);
				break;
			
			}
		} catch(BusinessException ex){
			model.addAttribute(SystemConstant.BEAN_NAME_MESSAGES, ResultMessages.error().add(ex.getMessage()));
			return "account/FmAccount";
		}
		redirectAttributes.addFlashAttribute(SystemConstant.BEAN_NAME_MESSAGES,ResultMessages.success().add("Modification''s successfull"));
		return "redirect:/admin/account/search?sort=lastUpdatedDate,desc";
	}
	@RequestMapping(value={"/delete"}, method = {RequestMethod.POST,RequestMethod.GET})
	public String processDeletion(AccountForm accountForm,BindingResult bindingResult,RedirectAttributes redirectAttributes, Model model){
		try{
			Account account = mapper.map(accountForm, Account.class);
			accountService.delete(account);
		} catch(BusinessException ex){
			redirectAttributes.addFlashAttribute(SystemConstant.BEAN_NAME_MESSAGES,ResultMessages.error().add(ex.getMessage()));
			return "redirect:/admin/account/search";
		}
		redirectAttributes.addFlashAttribute(SystemConstant.BEAN_NAME_MESSAGES,ResultMessages.success().add("Deletion''s successfull"));
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
			redirectAttributes.addFlashAttribute(SystemConstant.BEAN_NAME_MESSAGES,ResultMessages.error().add(ex.getMessage()));
			return "redirect:/admin/account/search";
		}
		
		redirectAttributes.addFlashAttribute(SystemConstant.BEAN_NAME_MESSAGES,ResultMessages.success().add("Approval's successfull"));
		return "redirect:/admin/account/search";
	}
}
