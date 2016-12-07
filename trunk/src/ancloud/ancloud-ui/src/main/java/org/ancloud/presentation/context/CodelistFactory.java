package org.ancloud.presentation.context;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.ancloud.domain.Resource;
import org.ancloud.repository.modules.ResourceRepository;
import org.springframework.beans.factory.FactoryBean;

public class CodelistFactory implements FactoryBean<Codelist> {
	
	@Inject
	ResourceRepository resourceRepository;
	
	public Codelist getObject() throws Exception {
		Iterable<Resource> resources = resourceRepository.findAll();
		Codelist codelist = new CodelistImpl();
		Map<String,Resource> resourceList = null;
		for(Resource resource:resources){
			if(codelist.containsKey(resource.getCategory())){
				resourceList = codelist.get(resource.getCategory());
			} else {
				resourceList = new HashMap<String,Resource>();
				codelist.put(resource.getCategory(),resourceList);
			}
			resourceList.put(resource.getKey(),resource);
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
