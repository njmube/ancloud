package org.bluebird.fw.presentation.factorybean;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.impl.DefaultMapperFactory;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;

public class BeanMapperFacadeFactory implements FactoryBean<MapperFacade> {
	public MapperFacade getObject() throws Exception {
		return new DefaultMapperFactory.Builder().build().getMapperFacade();
	}

	public Class<?> getObjectType() {
		return MapperFacade.class;
	}

	public boolean isSingleton() {
		return true;
	}
}
