package org.bluebird.fw.presentation;

import javax.inject.Inject;

import ma.glasnost.orika.MapperFacade;

public abstract class BaseController {
	@Inject
	protected MapperFacade mapper;
}
