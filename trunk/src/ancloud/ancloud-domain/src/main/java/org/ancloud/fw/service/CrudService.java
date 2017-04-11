package org.ancloud.fw.service;

import java.util.List;

import org.ancloud.domain.BaseModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

public interface CrudService<T> {

	public T findById(Long id);
	
	public T findByCode(String code);

	public T register(T entity);

	public List<T> register(List<T> entities);

	public T modify(T entity);
	
	public void delete(Long id);

	public Page<T> findAllByCriteria(Specification<T> criteria, Pageable pageable);
	
	public T checkExistence(Long id);
	
	public void checkConsistency(BaseModel entity, BaseModel existing);
}
