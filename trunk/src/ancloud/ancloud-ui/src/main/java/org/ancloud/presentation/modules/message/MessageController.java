package org.ancloud.presentation.modules.message;

import javax.inject.Inject;

import org.ancloud.service.modules.message.MessageService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping(value="message")
public class MessageController {
	
	@Inject
	MessageService messageService;
	
	@RequestMapping(value={"","search"}, method = {RequestMethod.GET})
	public String displaySearch(MessageSearchForm messageSearchForm, Model model){
		return "message/FsMessage";
	}
	
	@RequestMapping(value="search", method = {RequestMethod.POST})
	public String processSearch(MessageSearchForm accountSearchForm, Model model){
		return "message/FsMessage";
	}
}
