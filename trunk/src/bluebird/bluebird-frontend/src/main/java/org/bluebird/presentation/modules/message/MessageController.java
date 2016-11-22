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
import org.springframework.web.servlet.i18n.SessionLocaleResolver;


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
		return "forward:/message/search";
	}
}
