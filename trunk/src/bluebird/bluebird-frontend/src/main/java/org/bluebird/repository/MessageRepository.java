package org.bluebird.repository;

import java.util.List;

import org.bluebird.domain.Message;
import org.bluebird.domain.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Long> {
	public List<Message> findByProject(Project project);
	public List<Message> findByProjectAndMessageContaining(Project project,String message);
}
