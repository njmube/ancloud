package org.ancloud.presentation.service;

import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;

public interface SsePushService {

	public void registerEmitter(ResponseBodyEmitter emitter);

	public void unregisterEmitter(ResponseBodyEmitter emitter);
}