package org.ancloud.fw.presentation;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.Validator;

import ma.glasnost.orika.MapperFacade;

public abstract class BaseController {
	
	protected Logger logger = LoggerFactory.getLogger(this.getClass());

	@Inject
	protected MapperFacade mapper;
}
