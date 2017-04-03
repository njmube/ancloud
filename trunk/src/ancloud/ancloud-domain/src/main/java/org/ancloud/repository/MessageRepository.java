package org.ancloud.repository;

import java.util.List;

import org.ancloud.domain.Project;
import org.ancloud.domain.account.Account;
import org.ancloud.domain.message.Message;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface MessageRepository extends JpaRepository<Message, Long>, JpaSpecificationExecutor<Message> {
	public List<Message> findByProject(Project project);
	public Page<Message> findByProjectAndMessageContaining(Project project,Message message,Pageable pageable);
}
