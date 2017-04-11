package org.ancloud.fw.service;

import org.ancloud.domain.BaseModel;
import org.ancloud.fw.core.exception.BusinessException;
import org.ancloud.repository.BaseModelRepository;
import org.joda.time.DateTime;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public abstract class BaseService<T extends BaseModel> implements CrudService<T> {
	
	public abstract BaseModelRepository<T> getRepository();
	
	@Override
	public void checkConsistency(BaseModel entity, BaseModel existing) {
		if(entity.getVersion()!=existing.getVersion()){
			throw new BusinessException("There is another one modifying this. Try to fetch new data and make another submission.");
		}
		
	}
	
	@Override
	public T checkExistence(Long id) {
		T existing = this.getRepository().findOne(id);
		if(existing==null){
			throw new BusinessException("This entity didn't exist.");
		}
		return existing;
	}
	
	@Override
	public T findById(Long id) {
		return this.checkExistence(id);
	}
	
	@Override
	public Page<T> findAllByCriteria(Specification<T> criteria, Pageable pageable) {
		return this.getRepository().findAll(criteria,pageable);
	}
	
	@Override
	public void delete(Long id) {
		this.checkExistence(id);
		this.getRepository().deleteById(id, DateTime.now());
	}
	
	@Override
	public T findByCode(String code) {
		T existing = this.getRepository().findOneByCode(code);
		if (existing == null) {
			throw new BusinessException("This entity doesn't exist.");
		}
		return existing;
	}
}
