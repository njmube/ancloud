package org.bluebird.presentation.modules.message;

import java.util.Locale;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.bluebird.domain.Project;
import org.bluebird.domain.common.SessionConstant;
import org.bluebird.domain.modules.message.Message;
import org.bluebird.domain.modules.message.MessageSearchCriteria;
import org.bluebird.fw.presentation.resolver.JsonParam;
import org.bluebird.fw.presentation.resolver.Session;
import org.bluebird.presentation.modules.account.AccountSearchForm;
import org.bluebird.service.modules.message.MessageService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;


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
			message.setProject(project);
			message.setCountry(locale.getCountry());
			message.setLanguage(locale.getLanguage());
		}
		return messageService.findAll(message ,pageable);
	}
}
