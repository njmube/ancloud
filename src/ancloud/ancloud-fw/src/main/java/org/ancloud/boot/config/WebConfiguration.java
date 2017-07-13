package org.ancloud.boot.config;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.TimeZone;

import javax.inject.Inject;

import org.ancloud.fw.core.joda.DateTimeDeserializer;
import org.ancloud.fw.core.joda.DateTimeSerializer;
import org.ancloud.fw.presentation.i18n.CustomLocalValidatorFactoryBean;
import org.ancloud.presentation.codelist.CodelistFactory;
import org.joda.time.DateTime;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.xml.Jaxb2RootElementHttpMessageConverter;
import org.springframework.mobile.device.DeviceResolverHandlerInterceptor;
import org.springframework.mobile.device.DeviceResolverRequestFilter;
import org.springframework.mobile.device.DeviceWebArgumentResolver;
import org.springframework.security.web.method.annotation.AuthenticationPrincipalArgumentResolver;
import org.springframework.validation.Validator;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.joda.JodaModule;

@Configuration
public class WebConfiguration extends WebMvcConfigurerAdapter {

	@Inject
	MessageSource messageSource;
	
	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}
//	@Override
//	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
//		converters.add(new StringHttpMessageConverter());
//		super.configureMessageConverters(converters);
//	}
	
	@Bean
	public Jaxb2RootElementHttpMessageConverter xmlMessageConverter() {
		Jaxb2RootElementHttpMessageConverter xmlConverter = new Jaxb2RootElementHttpMessageConverter();
		return xmlConverter;
	}
	 	
	@Bean
	public Validator mvcValidator() {
		CustomLocalValidatorFactoryBean validator= new CustomLocalValidatorFactoryBean();
		validator.setValidationMessageSource(messageSource);
		return validator;
	}
	@Bean
	public AuthenticationPrincipalArgumentResolver authenticationPrincipalArgumentResolver(){
		return new AuthenticationPrincipalArgumentResolver();
	}
	
	@Bean
	public DeviceWebArgumentResolver  deviceWebArgumentResolver(){
		return new DeviceWebArgumentResolver();
	}
	
	@Bean
	public DeviceResolverRequestFilter  deviceResolverRequestFilter(){
		return new DeviceResolverRequestFilter();
	}
	
	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
		argumentResolvers.add(authenticationPrincipalArgumentResolver());
	}
	
	@Bean
	public DeviceResolverHandlerInterceptor deviceResolverHandlerInterceptor() {
		return new DeviceResolverHandlerInterceptor();
	}
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(deviceResolverHandlerInterceptor());
	}
	@Bean
	public MappingJackson2HttpMessageConverter customJackson2HttpMessageConverter() {
		MappingJackson2HttpMessageConverter jsonConverter = new MappingJackson2HttpMessageConverter();
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.sss");
		dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
		objectMapper.setDateFormat(dateFormat);
		SimpleModule module = new JodaModule();
		module.addSerializer(DateTime.class, new DateTimeSerializer());
		module.addDeserializer(DateTime.class, new DateTimeDeserializer());
		objectMapper.registerModule(module);
		jsonConverter.setObjectMapper(objectMapper);
		return jsonConverter;
	}
	@Bean
	public CodelistFactory codelistFactory(){
		return new CodelistFactory();
	}
}