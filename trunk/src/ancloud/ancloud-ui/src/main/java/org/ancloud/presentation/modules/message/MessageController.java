package org.ancloud.presentation.modules.message;

import javax.inject.Inject;

import org.ancloud.domain.Project;
import org.ancloud.domain.constant.SessionConstant;
import org.ancloud.domain.message.Message;
import org.ancloud.domain.message.MessageSearchCriteria;
import org.ancloud.fw.presentation.BaseController;
import org.ancloud.fw.presentation.resolver.Session;
import org.ancloud.service.message.MessageService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;


@Controller
@RequestMapping(value="message")
@SessionAttributes(types=MessageSearchForm.class)
public class MessageController extends BaseController{
	
	@Inject
	MessageService messageService;
	
	@RequestMapping(value={"","search"}, method = {RequestMethod.GET})
	public String displaySearch(MessageSearchForm messageSearchForm,  Model model,@Session(key=SessionConstant.SESSION_CURRENT_PROJECT) Project project, @PageableDefault Pageable pageable){
		MessageSearchCriteria criteria = mapper.map(messageSearchForm, MessageSearchCriteria.class);
//		TODO criteria.setProject(project);
		Page<Message> page = messageService.findAll(criteria, pageable);
		model.addAttribute("page",page);
		return "message/FsMessage";
	}
	
	@RequestMapping(value="search", method = {RequestMethod.POST})
	public String processSearch(MessageSearchForm messageSearchForm,Boolean lucence, Model model,@Session(key=SessionConstant.SESSION_CURRENT_PROJECT) Project project, @PageableDefault Pageable pageable){
		MessageSearchCriteria criteria = mapper.map(messageSearchForm, MessageSearchCriteria.class);
//		TODO criteria.setProject(project);
		Page<Message> page = null;
		if(lucence== null || !lucence){
			page = messageService.findAll(criteria, pageable);
		} else {
			page = messageService.fullTextSearch(criteria, pageable);
		}
		model.addAttribute("page",page);
		return "message/FsMessage";
	}
}
