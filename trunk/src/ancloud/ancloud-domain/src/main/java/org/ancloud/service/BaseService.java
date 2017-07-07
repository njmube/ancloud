package org.ancloud.service;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.ancloud.domain.BaseModel;
import org.ancloud.fw.core.exception.BusinessException;
import org.ancloud.repository.BaseRepository;
import org.joda.time.DateTime;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public abstract class BaseService<T extends BaseModel> implements CrudService<T> {
	
	@Inject
	BaseRepository<T> repository;
	
	public BaseRepository<T> getRepository(){
		return this.repository;
	}
	
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
		if(existing.getDeletedDate()!=null){
			throw new BusinessException("This entity was deleted.");
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
	public void delete(T baseModel) {
		this.deleteById(baseModel.getId());
	}
	
	@Override
	public void deleteById(Long id) {
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
	@Override
	public List<T> register(List<T> entities) {
		List<T> result = new ArrayList<T>();
		for(T entity: entities){
			result.add(this.register(entity));
		}
		return result;
	}
}
