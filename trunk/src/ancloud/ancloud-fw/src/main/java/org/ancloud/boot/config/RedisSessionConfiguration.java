package org.ancloud.boot.config;

import javax.inject.Inject;

import org.ancloud.presentation.service.RedisSessionService;
import org.ancloud.presentation.service.SessionService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.session.ExpiringSession;
import org.springframework.session.FindByIndexNameSessionRepository;
import org.springframework.session.security.SpringSessionBackedSessionRegistry;

@Profile(value = "spring-session-redis")
//@EnableRedisHttpSession(maxInactiveIntervalInSeconds = -1)
public class RedisSessionConfiguration extends WebSecurityConfigurerAdapter {
	@Value("${spring.redis.host}")
	private String REDIS_HOST;

	@Value("${spring.redis.port}")
	private int REDIS_PORT;

	@Inject
	FindByIndexNameSessionRepository<ExpiringSession> sessionRepository;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
	}

	@Bean
	@Profile(value = "spring-session")
	public SessionRegistry springSessionRegistry() {
		return new SpringSessionBackedSessionRegistry(this.sessionRepository);
	}
	
	@Bean
	public SessionService sessionService() {
		return new RedisSessionService();
	}

	@Bean
	public LettuceConnectionFactory connectionFactory() {
		LettuceConnectionFactory redisConnectionFactory = new LettuceConnectionFactory();
		redisConnectionFactory.setHostName(REDIS_HOST);
		redisConnectionFactory.setPort(REDIS_PORT);
		return redisConnectionFactory;
	}
}
