package org.ancloud.presentation.codelist;

import java.util.SortedMap;
import java.util.TreeMap;

import javax.inject.Inject;

import org.ancloud.domain.resource.Resource;
import org.ancloud.repository.jpa.ResourceRepository;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;

public class CodelistFactory implements FactoryBean<Codelist> {
	
	@Inject
	ResourceRepository resourceRepository;
	
	public Codelist getObject() throws Exception {
		Iterable<Resource> resources = resourceRepository.findAll();
		Codelist codelist = new CodelistImpl();
		SortedMap<String,Resource> resourceList = null;
		for(Resource resource:resources){
			if(codelist.containsKey(resource.getCategory())){
				resourceList = codelist.get(resource.getCategory());
			} else {
				resourceList = new TreeMap<String,Resource>();
				codelist.put(resource.getCategory(),resourceList);
			}
			resourceList.put(resource.getResourceKey(),resource);
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
