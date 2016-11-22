package org.bluebird.service.modules.message;

import org.bluebird.domain.modules.message.Message;
import org.bluebird.domain.modules.message.MessageSearchCriteria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MessageService {
	public Page<Message> findAll(MessageSearchCriteria message,Pageable pageable);
}
