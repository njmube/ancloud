package org.ancloud.presentation.modules.message;

import java.util.Locale;

import javax.inject.Inject;

import org.ancloud.domain.Project;
import org.ancloud.domain.constant.SessionConstant;
import org.ancloud.domain.message.Message;
import org.ancloud.domain.message.MessageSearchCriteria;
import org.ancloud.fw.presentation.resolver.JsonParam;
import org.ancloud.fw.presentation.resolver.Session;
import org.ancloud.service.message.MessageService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value="message")
public class MessageAjaxController {
	
	@Inject
	MessageService messageService;
	
	@RequestMapping(value="ajaxGetAllMessage", method = {RequestMethod.GET})
	@ResponseBody
	public Page<Message> ajaxGetAllMessage(@JsonParam("parameter") MessageSearchCriteria message,
											@Session(key=SessionConstant.SESSION_CURRENT_PROJECT) Project project,
											@Session(key=SessionConstant.SESSION_CURRENT_LOCALE) Locale locale,
											@PageableDefault Pageable pageable){
		if(message != null){
//			TODO message.setProject(project);
			message.setCountry(locale.getCountry());
			message.setLanguage(locale.getLanguage());
		}
		return messageService.findAll(message ,pageable);
	}
}
