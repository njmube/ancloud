package org.bluebird.presentation.module.home;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {
	
	@RequestMapping(value="/", method = RequestMethod.GET)
	public String root(){
		return "redirect:/dashboard/dashboard";
	}
	
	@RequestMapping(value="/login", method = RequestMethod.GET)
	public String login(){
		return "home/login";
	}
	
	@RequestMapping(value="/login", method = RequestMethod.GET,params={"error"})
	public String login(@RequestParam("error") String error){
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
