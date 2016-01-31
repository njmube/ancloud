package org.bluebird.presentation.mapper;

import javax.inject.Inject;

import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MappingContext;

import org.bluebird.domain.BaseModel;
import org.bluebird.domain.Project;
import org.bluebird.domain.common.SessionConstant;
import org.bluebird.fw.core.service.SessionService;
import org.springframework.stereotype.Component;

public class CommonMapper extends CustomMapper<Object,BaseModel> {
	
	@Inject
	SessionService sessionService;
	
	@Override
	public void mapAtoB(Object obj, BaseModel obj1, MappingContext mappingcontext) {
		obj1.setProject(sessionService.get(SessionConstant.SESSION_CURRENT_PROJECT,Project.class));
	}

	public void mapBtoA(BaseModel obj, Object obj1, MappingContext mappingcontext) {
	}
}
