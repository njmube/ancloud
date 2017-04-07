package org.ancloud.presentation.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;

@Component
public class SsePushServiceImpl implements SsePushService,ApplicationListener<SseEvent> {

	private Logger logger = LoggerFactory.getLogger(SsePushServiceImpl.class);
	
	private List<ResponseBodyEmitter> emitters = new ArrayList<ResponseBodyEmitter>();
	
	@Override
	public void registerEmitter(ResponseBodyEmitter emitter) {
		if(!emitters.contains(emitter)){
			emitters.add(emitter);
		} else {
			logger.info("Emitter's already registered.");
		}
	}

	@Override
	public void unregisterEmitter(ResponseBodyEmitter emitter) {
		if(emitters.contains(emitter)){
			emitters.remove(emitter);
		} else {
			logger.info("Emitter wasn't registered.");
		}
	}

	@Override
	public void onApplicationEvent(SseEvent event) {
	}
}
