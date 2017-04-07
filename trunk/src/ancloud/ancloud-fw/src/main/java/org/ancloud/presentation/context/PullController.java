package org.ancloud.presentation.context;

import javax.inject.Inject;

import org.ancloud.presentation.service.SsePushService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;

@Controller
@RequestMapping("/pull")
public class PullController {
	
	@Inject
	SsePushService pushService;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseBodyEmitter handle() {
		final ResponseBodyEmitter emitter = new ResponseBodyEmitter();
		emitter.onCompletion(new Runnable() {
			@Override
			public void run() {
				pushService.unregisterEmitter(emitter);
			}
		});
		pushService.registerEmitter(emitter);
		return emitter;
	}
}
