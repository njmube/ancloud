package org.ancloud.service.modules.message;

import org.ancloud.domain.modules.message.Message;
import org.ancloud.domain.modules.message.MessageSearchCriteria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MessageService {
	public Page<Message> findAll(MessageSearchCriteria message,Pageable pageable);
}
