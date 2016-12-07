package org.ancloud.presentation.mapper;

import javax.inject.Inject;

import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MappingContext;

import org.ancloud.domain.BaseModel;
import org.ancloud.domain.Project;
import org.ancloud.domain.common.SessionConstant;
import org.ancloud.fw.core.service.SessionService;
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
