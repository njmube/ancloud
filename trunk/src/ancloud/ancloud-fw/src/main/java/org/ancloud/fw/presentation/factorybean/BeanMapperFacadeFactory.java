package org.ancloud.fw.presentation.factorybean;

import org.joda.time.DateTime;
import org.springframework.beans.factory.FactoryBean;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.converter.builtin.PassThroughConverter;
import ma.glasnost.orika.impl.DefaultMapperFactory;

public class BeanMapperFacadeFactory implements FactoryBean<MapperFacade> {
	public MapperFacade getObject() throws Exception {
		DefaultMapperFactory factory = new DefaultMapperFactory.Builder().build();
		factory.getConverterFactory().registerConverter(new PassThroughConverter(DateTime.class));
		return factory.getMapperFacade();
	}

	public Class<?> getObjectType() {
		return MapperFacade.class;
	}

	public boolean isSingleton() {
		return true;
	}
}
