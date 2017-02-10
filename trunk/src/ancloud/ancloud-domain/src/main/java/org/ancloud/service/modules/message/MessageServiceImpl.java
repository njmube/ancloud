package org.ancloud.service.modules.message;

import javax.inject.Inject;

import org.ancloud.domain.modules.message.Message;
import org.ancloud.domain.modules.message.MessageSearchCriteria;
import org.ancloud.repository.modules.MessageFulltextRepository;
import org.ancloud.repository.modules.MessageRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MessageServiceImpl implements MessageService {

	@Inject 
	MessageRepository messageRepository;
	
	@Inject
	MessageFulltextRepository messageFulltextRepository;
	
	
	@Override
	public Page<Message> findAll(MessageSearchCriteria message,Pageable pageable) {
		return messageRepository.findAll(message, pageable);
	}



	@Override
	public Page<Message> fullTextSearch(MessageSearchCriteria criteria, Pageable pageable) {
		return messageFulltextRepository.search(criteria.toString(),pageable);
	}
}