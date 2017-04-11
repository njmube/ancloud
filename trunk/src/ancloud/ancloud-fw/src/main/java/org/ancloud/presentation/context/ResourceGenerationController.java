package org.ancloud.presentation.context;

import javax.inject.Inject;

import org.ancloud.fw.presentation.i18n.InitializableMessageSource;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/resources")
public class ResourceGenerationController {
	
	@Inject
	MessageSource messageSource;
	
	@RequestMapping(value="jsMessageSource.js",method = RequestMethod.GET)
	public String generateJsMessageSource(String locale,Model model) {
		if(messageSource instanceof InitializableMessageSource){
			model.addAttribute("messages",((InitializableMessageSource)messageSource).getMessages());
		}
		
		return "jsMessageSource";
	}
}
