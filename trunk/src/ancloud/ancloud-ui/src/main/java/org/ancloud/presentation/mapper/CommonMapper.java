package org.ancloud.presentation.mapper;

import javax.inject.Inject;

import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MappingContext;

import org.ancloud.domain.ProjectBaseModel;
import org.ancloud.domain.utils.SessionConstant;
import org.ancloud.domain.Project;
import org.ancloud.fw.core.service.SessionService;
import org.springframework.stereotype.Component;

public class CommonMapper extends CustomMapper<Object,ProjectBaseModel> {
	
	@Inject
	SessionService sessionService;
	
	@Override
	public void mapAtoB(Object obj, ProjectBaseModel obj1, MappingContext mappingcontext) {
		obj1.setProject(sessionService.get(SessionConstant.SESSION_CURRENT_PROJECT,Project.class));
	}

	public void mapBtoA(ProjectBaseModel obj, Object obj1, MappingContext mappingcontext) {
	}
}
