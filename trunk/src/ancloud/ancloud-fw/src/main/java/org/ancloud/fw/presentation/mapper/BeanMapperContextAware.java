
package org.ancloud.fw.presentation.mapper;

import java.util.Map;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import ma.glasnost.orika.Converter;
import ma.glasnost.orika.Filter;
import ma.glasnost.orika.Mapper;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.converter.builtin.EnumConverter;
import ma.glasnost.orika.impl.DefaultMapperFactory;

@Component
public class BeanMapperContextAware implements FactoryBean<MapperFacade>,ApplicationContextAware {

	DefaultMapperFactory factory = new DefaultMapperFactory.Builder().build();
	
	@Override
	public MapperFacade getObject() throws Exception {
		return factory.getMapperFacade();
	}

	@Override
	public Class<?> getObjectType() {
		return MapperFacade.class;
	}

	@Override
	public boolean isSingleton() {
		return true;
	}

	@Override
	public void setApplicationContext(final ApplicationContext applicationContext) throws BeansException {
		addAllSpringBeans(applicationContext);
	}

	@SuppressWarnings("rawtypes")
	private void addAllSpringBeans(final ApplicationContext applicationContext) {
		
		final Map<String, Converter> converters = applicationContext.getBeansOfType(Converter.class);
		for (final Converter converter : converters.values()) {
			addConverter(converter);
		}

		final Map<String, Mapper> mappers = applicationContext.getBeansOfType(Mapper.class);
		for (final Mapper mapper : mappers.values()) {
			addMapper(mapper);
		}
		
		final Map<String, Filter> filters = applicationContext.getBeansOfType(Filter.class);
		for (final Filter filter : filters.values()) {
			factory.registerFilter(filter);
		}
		this.addConverter(new EnumConverter());
	}

	public void addConverter(final Converter<?, ?> converter) {
		factory.getConverterFactory().registerConverter(converter);
	}

	@SuppressWarnings({"unchecked", "rawtypes"})
	public void addMapper(final Mapper<?, ?> mapper) {
		factory.classMap(mapper.getAType(), mapper.getBType())
				.mapNulls(true)
				.byDefault()
				.customize((Mapper) mapper)
				.register();
	}


}
