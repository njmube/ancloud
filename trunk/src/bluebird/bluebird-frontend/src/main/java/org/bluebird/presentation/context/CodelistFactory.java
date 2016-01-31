package org.bluebird.presentation.context;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.bluebird.domain.Resource;
import org.bluebird.repository.ResourceRepository;
import org.springframework.beans.factory.FactoryBean;

public class CodelistFactory implements FactoryBean<Codelist> {
	
	@Inject
	ResourceRepository resourceRepository;
	
	public Codelist getObject() throws Exception {
		Iterable<Resource> resources = resourceRepository.findAll();
		Codelist codelist = new CodelistImpl();
		List<Resource> resourceList = null;
		for(Resource resource:resources){
			if(codelist.containsKey(resource.getCategory())){
				resourceList = codelist.get(resource.getCategory());
			} else {
				resourceList = new ArrayList<Resource>();
				codelist.put(resource.getCategory(),resourceList);
			}
			resourceList.add(resource);
		}
		return codelist ;
	}

	public Class<?> getObjectType() {
		return Codelist.class;
	}

	public boolean isSingleton() {
		return true;
	}
}
