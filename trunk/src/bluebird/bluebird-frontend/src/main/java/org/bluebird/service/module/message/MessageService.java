package org.bluebird.service.module.message;

import java.util.List;

import org.bluebird.domain.Message;
import org.bluebird.domain.Project;

public interface MessageService {
	public List<Message> findAll(Project project,String message);
}
