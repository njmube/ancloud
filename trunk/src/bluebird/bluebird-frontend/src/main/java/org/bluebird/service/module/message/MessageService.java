package org.bluebird.service.module.message;

import org.bluebird.domain.Project;
import org.bluebird.domain.module.message.Message;
import org.bluebird.domain.module.message.MessageSearchCriteria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MessageService {
	public Page<Message> findAll(Project project,MessageSearchCriteria message,Pageable pageable);
}
