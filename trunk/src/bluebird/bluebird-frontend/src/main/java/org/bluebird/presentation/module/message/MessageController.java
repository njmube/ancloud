package org.bluebird.presentation.module.message;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.bluebird.domain.Project;
import org.bluebird.domain.common.SessionConstant;
import org.bluebird.domain.module.message.Message;
import org.bluebird.domain.module.message.MessageSearchCriteria;
import org.bluebird.fw.presentation.resolver.JsonParam;
import org.bluebird.fw.presentation.resolver.Session;
import org.bluebird.service.module.message.MessageService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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
	public Page<Message> ajaxGetAllMessage(@JsonParam("parameter") MessageSearchCriteria message,@Session(key=SessionConstant.SESSION_CURRENT_PROJECT) Project project,@PageableDefault Pageable pageable){
		return messageService.findAll(project, message ,pageable);
	}
}
