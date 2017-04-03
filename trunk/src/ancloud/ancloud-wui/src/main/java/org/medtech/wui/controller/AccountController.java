package org.medtech.wui.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.medtech.domain.modules.account.Account;
import org.medtech.domain.modules.account.AccountSearchCriteria;
import org.medtech.domain.modules.account.enums.AccountStatus;
import org.medtech.domain.modules.medtech.Doctor;
import org.medtech.domain.modules.medtech.Nurse;
import org.medtech.domain.modules.medtech.Patient;
import org.medtech.fw.core.exception.BusinessException;
import org.medtech.fw.core.service.SessionService;
import org.medtech.fw.presentation.BaseController;
import org.medtech.fw.presentation.message.ResultMessages;
import org.medtech.presentation.form.AccountForm;
import org.medtech.service.modules.account.AccountService;
import org.medtech.wui.form.AccountSearchForm;
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
@RequestMapping(value="/account")
@SessionAttributes(types={AccountSearchForm.class})
public class AccountController extends BaseController {
	
	@Inject
	AccountService accountService;
	
	@Inject
	SessionService sessionService;
	
	@InitBinder(value="accountSearchForm")
	public void initSearchForm(WebDataBinder binder){
		
	}
	
	@RequestMapping(value={"/show-info"}, method = RequestMethod.GET)
	public String showAccountInfo(AccountForm accountForm,Model model){
		
		Account account = accountService.findAccountById(accountForm.getId(),accountForm.getAccountType());
		model.addAttribute("account", mapper.map(account, AccountForm.class));
		return "account/FiAccountInfo";
	}
	@RequestMapping(value={"","/search"}, method = {RequestMethod.GET})
	public String displaySearch(AccountSearchForm accountSearchForm, Model model, @PageableDefault Pageable pageable,HttpServletRequest req){
		@SuppressWarnings("unused")
		HttpSession session = req.getSession();
		AccountSearchCriteria criteria = mapper.map(accountSearchForm, AccountSearchCriteria.class);
		Page<Account> page = accountService.findAllAccountByCriteria(criteria, pageable);
		model.addAttribute("page",page);
		return "account/FsAccount";
	}
	
	@RequestMapping(value="/search", method = {RequestMethod.POST})
	public String processSearch(AccountSearchForm accountSearchForm, Model model, @PageableDefault Pageable pageable){
		AccountSearchCriteria criteria = mapper.map(accountSearchForm, AccountSearchCriteria.class);
		Page<Account> page = accountService.findAllAccountByCriteria(criteria, pageable);
		model.addAttribute("page",page);
		return "account/FsAccount";
	} 
	
	@RequestMapping(value={"","/register"}, method = {RequestMethod.GET})
	public String displayRegister(AccountForm accountForm, Model model, @PageableDefault Pageable pageable){
		
		return "account/FrAccount";
	}
	
	@RequestMapping(value={"","/register"}, method = {RequestMethod.POST})
	public String processRegister(@Valid AccountForm accountForm,BindingResult bindingResult, Model model,RedirectAttributes redirectAttributes, @PageableDefault Pageable pageable){
		if(bindingResult.hasErrors()){
			return "account/FrAccount";
		}
		try{
			switch(accountForm.getAccountType()){
			case Administrator:
				Account account = mapper.map(accountForm, Account.class);
				accountService.registerNewAccount(account);
				break;
			case Doctor:
				Doctor doctor = mapper.map(accountForm, Doctor.class);
				accountService.registerNewDoctor(doctor);
				break;
			case Nurse:
				Nurse nurse = mapper.map(accountForm, Nurse.class);
				accountService.registerNewNurse(nurse);
				break;
			case Patient:
				Patient patient = mapper.map(accountForm, Patient.class);
				accountService.registerNewPatient(patient);
				break;
			
			}
		} catch(Exception ex){
			model.addAttribute("messages",
					ResultMessages.error().add(ex.getMessage()));
			ex.printStackTrace();
			return "account/FrAccount";
		}
		redirectAttributes.addFlashAttribute("messages",ResultMessages.success().add("Registration's successfull"));
		return "redirect:/account/search?sort=createdDate,desc";
	}
	
	@RequestMapping(value={"","/modify"}, method = {RequestMethod.GET})
	public String displayModify(AccountForm accountForm, Model model, @PageableDefault Pageable pageable){
		Account account = accountService.findAccountById(accountForm.getId(),accountForm.getAccountType());
		model.addAttribute("accountForm", mapper.map(account, AccountForm.class));
		return "account/FmAccount";
	}
	
	@RequestMapping(value={"","/modify"}, method = {RequestMethod.POST})
	public String processModify(@Valid AccountForm accountForm,BindingResult bindingResult,RedirectAttributes redirectAttributes, Model model, @PageableDefault Pageable pageable){
		if(bindingResult.hasErrors()){
			return "account/FmAccount";
		}
		try{
			switch(accountForm.getAccountType()){
			case Administrator:
				Account account = mapper.map(accountForm, Account.class);
				accountService.modifyAccount(account);
				break;
			case Doctor:
				Doctor doctor = mapper.map(accountForm, Doctor.class);
				accountService.modifyDoctor(doctor);
				break;
			case Nurse:
				Nurse nurse = mapper.map(accountForm, Nurse.class);
				accountService.modifyNurse(nurse);
				break;
			case Patient:
				Patient patient = mapper.map(accountForm, Patient.class);
				accountService.modifyPatient(patient);
				break;
			
			}
		} catch(BusinessException ex){
			model.addAttribute("messages",
					ResultMessages.error().add(ex.getMessage()));
			return "account/FmAccount";
		}
		redirectAttributes.addFlashAttribute("messages",ResultMessages.success().add("Registration's successfull"));
		return "redirect:/account/search?sort=lastUpdatedDate,desc";
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
			redirectAttributes.addFlashAttribute("messages",
					ResultMessages.error().add(ex.getMessage()));
			return "redirect:/account/search";
		}
		
		redirectAttributes.addFlashAttribute("messages",ResultMessages.success().add("Approval's successfull"));
		return "redirect:/account/search";
	}
}
