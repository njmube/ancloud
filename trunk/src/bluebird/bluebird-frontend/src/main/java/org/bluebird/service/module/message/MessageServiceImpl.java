package org.bluebird.service.module.message;

import java.util.List;

import javax.inject.Inject;

import org.bluebird.domain.Project;
import org.bluebird.domain.module.message.Message;
import org.bluebird.domain.module.message.MessageSearchCriteria;
import org.bluebird.repository.MessageRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MessageServiceImpl implements MessageService {

	@Inject 
	MessageRepository messageRepository;
	
	@Override
	public Page<Message> findAll(Project project,MessageSearchCriteria message,Pageable pageable) {
		message.setProject(project);
		return messageRepository.findAll(message, pageable);
	}
}
