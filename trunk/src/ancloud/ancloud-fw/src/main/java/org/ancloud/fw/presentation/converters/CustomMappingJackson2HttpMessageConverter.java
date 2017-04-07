package org.ancloud.fw.presentation.converters;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import com.fasterxml.jackson.databind.SerializationFeature;

public class CustomMappingJackson2HttpMessageConverter extends MappingJackson2HttpMessageConverter {
	public CustomMappingJackson2HttpMessageConverter() {
		super();
		objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
	}
}
