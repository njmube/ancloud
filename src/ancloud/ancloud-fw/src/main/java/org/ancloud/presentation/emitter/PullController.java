package org.ancloud.presentation.emitter;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.ancloud.presentation.service.SsePushService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;

@Controller
@RequestMapping("/polling")
public class PullController {
	
	@Inject
	SsePushService pushService;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseBodyEmitter handle(HttpSession session) {
		final ResponseBodyEmitter emitter = new ResponseBodyEmitter();
		pushService.registerEmitter(emitter);
		emitter.onCompletion(new Runnable() {
			@Override
			public void run() {
				pushService.unregisterEmitter(emitter);
			}
		});
		return emitter;
	}
}
