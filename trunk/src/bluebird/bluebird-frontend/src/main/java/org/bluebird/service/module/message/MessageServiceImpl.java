package org.bluebird.service.module.message;

import java.util.List;

import javax.inject.Inject;

import org.bluebird.domain.Message;
import org.bluebird.domain.Project;
import org.bluebird.repository.MessageRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MessageServiceImpl implements MessageService {

	@Inject 
	MessageRepository messageRepository;
	
	@Override
	public List<Message> findAll(Project project,String message) {
		return messageRepository.findByProjectAndMessageContaining(project, message);
	}
}
