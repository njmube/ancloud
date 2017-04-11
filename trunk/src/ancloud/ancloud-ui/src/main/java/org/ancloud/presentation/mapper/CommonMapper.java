package org.ancloud.presentation.mapper;

import javax.inject.Inject;

import org.ancloud.domain.Project;
import org.ancloud.domain.ProjectBaseModel;
import org.ancloud.domain.constant.SessionConstant;
import org.ancloud.fw.core.service.SessionService;

import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MappingContext;

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
