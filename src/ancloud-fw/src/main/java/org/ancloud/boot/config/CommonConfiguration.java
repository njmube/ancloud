package org.ancloud.boot.config;

import javax.sql.DataSource;

import org.ancloud.fw.presentation.helper.LocaleHelpers;
import org.ancloud.fw.presentation.i18n.InitializableMessageSource;
import org.ancloud.fw.presentation.i18n.JdbcMessageProvider;
import org.ancloud.fw.presentation.i18n.MessageProvider;
import org.ancloud.fw.presentation.mapper.BeanMapperContextAware;
import org.ancloud.presentation.context.ApplicationContextListener;
import org.springframework.boot.web.filter.OrderedCharacterEncodingFilter;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

@Configuration
public class CommonConfiguration {

	@Bean
	public SessionLocaleResolver localeResolver() {
		SessionLocaleResolver localeResolver = new SessionLocaleResolver();
		localeResolver.setDefaultLocale(LocaleHelpers.toLocale("en_US"));
		return localeResolver;
	}

	@Bean
	public LocaleChangeInterceptor localeChangeInterceptor() {
		LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
		localeChangeInterceptor.setParamName("language");
		return localeChangeInterceptor;
	}

	@Bean
	public OrderedCharacterEncodingFilter characterEncodingFilter() {
		OrderedCharacterEncodingFilter characterEncodingFilter = new OrderedCharacterEncodingFilter();
		characterEncodingFilter.setForceEncoding(true);
		characterEncodingFilter.setEncoding("UTF-8");
		characterEncodingFilter.setOrder(Ordered.HIGHEST_PRECEDENCE);
		return characterEncodingFilter;
	}
	@Bean
	public MessageProvider messageProvider(DataSource dataSource){
		return new JdbcMessageProvider(dataSource);
	}
	
	@Bean
	MessageSource messageSource(){
		return new InitializableMessageSource();
	}
	@Bean
	public ApplicationContextListener contextListener(){
		return new ApplicationContextListener();
	}
	@Bean
	public PasswordEncoder passwordEncoder() {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		return passwordEncoder;
	}
}