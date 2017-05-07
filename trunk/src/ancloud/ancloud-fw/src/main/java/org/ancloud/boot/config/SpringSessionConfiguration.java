package org.ancloud.boot.config;

import org.ancloud.fw.core.service.SessionService;
import org.ancloud.presentation.service.WebSessionService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.session.MapSessionRepository;
import org.springframework.session.config.annotation.web.http.EnableSpringHttpSession;


@EnableSpringHttpSession
//@Profile(value = "spring-session")
public class SpringSessionConfiguration extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
	}

	@Bean
	public SessionRegistry sessionRegistry() {
		return new SessionRegistryImpl();
	}
	
	@Bean
	public SessionService sessionService() {
		return new WebSessionService();
	}

	@Bean
	public MapSessionRepository sessionRepository() {
		return new MapSessionRepository();
	}

}
