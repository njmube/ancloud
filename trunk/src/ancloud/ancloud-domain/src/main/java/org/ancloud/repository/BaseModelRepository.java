package org.ancloud.repository;

import java.util.List;

import org.joda.time.DateTime;
import org.ancloud.domain.BaseModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface BaseModelRepository<T extends BaseModel> extends JpaRepository<T, Long>, JpaSpecificationExecutor<T>{

	@Modifying
	@Query("DELETE FROM #{#entityName} A WHERE A.id NOT IN ?1")
	Integer deleteByIdNotIn(List<Long> ids);
	
	@Modifying
	@Query("UPDATE #{#entityName} A SET A.deletedDate = ?2 WHERE A.id = ?1")
	Integer deleteById(Long id,DateTime date);
	
	T findOneByCode(String code);
}
