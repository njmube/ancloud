
package org.ancloud.fw.presentation.factorybean;

import java.util.Map;

import ma.glasnost.orika.Converter;
import ma.glasnost.orika.Filter;
import ma.glasnost.orika.Mapper;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.ObjectFactory;
import ma.glasnost.orika.converter.builtin.PassThroughConverter;
import ma.glasnost.orika.impl.ConfigurableMapper;
import ma.glasnost.orika.impl.DefaultMapperFactory;

import org.joda.time.DateTime;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class BeanMapperContextAware extends ConfigurableMapper implements ApplicationContextAware {

	private MapperFactory factory;

	@Override
	protected void configureFactoryBuilder(final DefaultMapperFactory.Builder factoryBuilder) {
	}

	@Override
	protected void configure(final MapperFactory factory) {
		this.factory = factory;
		this.factory.getConverterFactory().registerConverter(new PassThroughConverter(DateTime.class));
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