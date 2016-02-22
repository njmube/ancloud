package org.bluebird.presentation.module.message;

import java.util.List;

import javax.inject.Inject;

import org.bluebird.domain.Message;
import org.bluebird.domain.Project;
import org.bluebird.fw.presentation.resolver.JsonParam;
import org.bluebird.fw.presentation.resolver.Session;
import org.bluebird.service.module.message.MessageService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping(value="message")
public class MessageController {
	
	@Inject
	MessageService messageService;
	
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
	
	@RequestMapping(value="ajaxGetAllMessage", method = {RequestMethod.GET})
	@ResponseBody
	public List<Message> ajaxGetAllMessage(@JsonParam("parameter") Message parameter,@Session(key="project") Project project){
		return messageService.findAll(project, parameter.getMessage());
	}
}
