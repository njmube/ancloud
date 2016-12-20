package org.ancloud.repository.modules;

import java.util.List;

import org.ancloud.domain.Project;
import org.ancloud.domain.modules.account.Account;
import org.ancloud.domain.modules.message.Message;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface MessageRepository extends JpaRepository<Message, Long>, JpaSpecificationExecutor<Message> {
	public List<Message> findByProject(Project project);
	public Page<Message> findByProjectAndMessageContaining(Project project,Message message,Pageable pageable);
}
