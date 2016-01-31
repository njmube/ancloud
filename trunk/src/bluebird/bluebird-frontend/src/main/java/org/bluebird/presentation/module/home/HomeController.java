package org.bluebird.presentation.module.home;

import org.bluebird.domain.common.SystemCodeConstant;
import org.bluebird.fw.core.message.ResultMessage;
import org.bluebird.fw.core.message.ResultMessages;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {
	
	@RequestMapping(value={"/","/home"}, method = RequestMethod.GET)
	public String root(){
		return "redirect:/dashboard/dashboard";
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
	public String displayRegistration(){
		return "home/register";
	}
	
	@RequestMapping(value="/register", method = RequestMethod.POST)
	public String register(){
		return "home/register";
	}
}
