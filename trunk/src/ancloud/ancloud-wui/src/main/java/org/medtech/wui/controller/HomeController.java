package org.medtech.wui.controller;

import javax.inject.Inject;
import javax.validation.Valid;

import org.medtech.domain.modules.account.enums.AccountType;
import org.medtech.domain.modules.medtech.Doctor;
import org.medtech.domain.modules.medtech.Patient;
import org.medtech.domain.utils.SystemCodeConstant;
import org.medtech.fw.presentation.BaseController;
import org.medtech.fw.presentation.message.ResultMessage;
import org.medtech.fw.presentation.message.ResultMessages;
import org.medtech.presentation.form.AccountForm;
import org.medtech.service.modules.account.AccountService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class HomeController extends BaseController {
	
	@Inject
	AccountService accountService;
	
	@RequestMapping(value={"/","/home"}, method = RequestMethod.GET)
	public String root(AccountForm accountForm){
		return "index";
//		return "redirect:/account/search";
	}
	
	@RequestMapping(value="/login", method = RequestMethod.GET)
	public String login(){
		return "home/login";
	}
	
	@RequestMapping(value="/login", method = RequestMethod.GET,params={"error"})
	public String login(@RequestParam("error") String error,Model model){
		model.addAttribute("messages",
					ResultMessages.error().add(ResultMessage.fromCode(SystemCodeConstant.ERR_SYS_LOGIN_USERNOTFOUND)));
		return "home/login";
	}

	@RequestMapping(value="/register", method = RequestMethod.GET)
	public String register(AccountForm accountForm, Model model){
		return "home/register";
	}

	@RequestMapping(value={"","/register"}, method = {RequestMethod.POST})
	public String processRegister(@Valid AccountForm accountForm,BindingResult bindingResult, Model model,RedirectAttributes redirectAttributes, @PageableDefault Pageable pageable){
		if(bindingResult.hasErrors()){
			return "home/register";
		}
		try{
			if(accountForm.getAccountType()==AccountType.Doctor){
				Doctor account = mapper.map(accountForm, Doctor.class);
				accountService.registerNewDoctor(account);
			} else if(accountForm.getAccountType()==AccountType.Patient){
				Patient account = mapper.map(accountForm, Patient.class);
				accountService.registerNewPatient(account);
			} else{
				model.addAttribute("messages",ResultMessages.error().add("This account type is not allowed."));
				return "home/register";
			}
		} catch(Exception ex){
			model.addAttribute("messages",
					ResultMessages.error().add(ex.getMessage()));
			return "home/register";
		}
		if(accountForm.getAccountType()==AccountType.Doctor){
			redirectAttributes.addFlashAttribute("messages",ResultMessages.success().add("Registration's successfull. Your account must be approved before login."));
		} else {
			redirectAttributes.addFlashAttribute("messages",ResultMessages.success().add("Registration's successfull"));
		}
		return "redirect:/login";
	}
}
