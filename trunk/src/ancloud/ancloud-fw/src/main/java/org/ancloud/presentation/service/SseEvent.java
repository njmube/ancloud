package org.ancloud.presentation.service;

import org.springframework.context.ApplicationEvent;

public class SseEvent extends ApplicationEvent{

	public SseEvent(Object source) {
		super(source);
	}

	private static final long serialVersionUID = 4780106968650310626L;

}
