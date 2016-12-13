package org.ancloud.repository.modules;

import java.util.List;

import org.ancloud.domain.ProjectBaseModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface CommonRepository<T extends ProjectBaseModel> extends JpaRepository<T, Long> {

	@Modifying
	@Query("DELETE FROM #{#entityName} A WHERE A.id NOT IN ?1")
	Integer deleteByIdNotIn(List<Long> ids);
}