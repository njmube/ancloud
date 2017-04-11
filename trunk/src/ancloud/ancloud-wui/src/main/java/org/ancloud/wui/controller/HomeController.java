package org.ancloud.wui.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.ancloud.domain.constant.SystemCodeConstant;
import org.ancloud.fw.presentation.BaseController;
import org.ancloud.fw.presentation.message.ResultMessage;
import org.ancloud.fw.presentation.message.ResultMessages;
import org.ancloud.presentation.form.AccountForm;
import org.ancloud.service.account.AccountService;
import org.springframework.boot.autoconfigure.web.ErrorController;
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
public class HomeController extends BaseController implements ErrorController{
	
	@Inject
	AccountService accountService;
	
	@RequestMapping(value={"/","/home"}, method = RequestMethod.GET)
	public String root(AccountForm accountForm){
		return "index";
//		return "redirect:/account/search";
	}
	@RequestMapping(value={"/error"}, method = RequestMethod.GET)
	public String error(HttpServletRequest request,HttpServletResponse response,Model model){
		model.addAttribute("statusCode", response.getStatus());
		return "home/error";
	}
	@Override
	public String getErrorPath() {
		return "/error";
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

		} catch(Exception ex){
			model.addAttribute("messages",
					ResultMessages.error().add(ex.getMessage()));
			return "home/register";
		}
		redirectAttributes.addFlashAttribute("messages",ResultMessages.success().add("Registration's successfull"));
		return "redirect:/login";
	}
}
