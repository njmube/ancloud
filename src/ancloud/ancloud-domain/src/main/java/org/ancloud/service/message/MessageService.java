package org.ancloud.service.message;

import org.ancloud.domain.message.Message;
import org.ancloud.domain.message.MessageSearchCriteria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MessageService {
	public Page<Message> findAll(MessageSearchCriteria message,Pageable pageable);

	public Page<Message> fullTextSearch(MessageSearchCriteria criteria, Pageable pageable);
}
