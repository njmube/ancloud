package org.ancloud.presentation.mapper;

import javax.inject.Inject;

import org.ancloud.domain.BaseModel;
import org.ancloud.domain.Project;
import org.ancloud.domain.common.SessionConstant;
import org.ancloud.fw.core.service.SessionService;
import org.springframework.stereotype.Component;

import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.NullFilter;
import ma.glasnost.orika.metadata.Property;
import ma.glasnost.orika.metadata.Type;

public class CommonFilter extends NullFilter<Object, BaseModel> {

	@Inject
	SessionService sessionService;
	
	@Override
	public boolean appliesTo(Property source, Property destination) {
		return true;
	}
	
	@Override
	public boolean filtersDestination() {
		return true;
	}
	
	@Override
	public <D extends BaseModel> D filterDestination(D destinationValue,
			final Type<?> sourceType, final String sourceName,
			final Type<D> destType, final String destName,
			final MappingContext mappingContext) {
		
		((BaseModel) destinationValue).setProject(sessionService.get(
				SessionConstant.SESSION_CURRENT_PROJECT, Project.class));

		return destinationValue;
	}

}