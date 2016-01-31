package org.bluebird.presentation.module.message;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping(value="message")
public class MessageController {
	
	@RequestMapping(method = {RequestMethod.GET})
	public String display(){
		return "forward:/message/search";
	}
	
	@RequestMapping(value="search", method = {RequestMethod.GET})
	public String displaySearch(){
		return "message/messageSearchForm";
	}
	
	@RequestMapping(value="search", method = {RequestMethod.POST})
	public String processSearch(){
		return "message/messageSearchForm";
	}
	
}
