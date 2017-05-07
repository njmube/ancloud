package org.ancloud.wui;

import java.lang.reflect.Method;

import javax.inject.Inject;

import org.ancloud.fw.core.service.SessionService;
import org.ancloud.presentation.service.RedisSessionService;
import org.ancloud.service.authentication.AuthenticationSuccessHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 60*30)
//@EnableSpringHttpSession(maxInactiveIntervalInSeconds = 60*60)
@EnableCaching
@EnableJpaAuditing(auditorAwareRef="auditorProvider")
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Value("${spring.redis.host}")
	private String REDIS_HOST;

	@Value("${spring.redis.port}")
	private int REDIS_PORT;

	@Inject
	UserDetailsService userDetailsService;

	@Value("${ancloud.security.session.max}")
	private int SESSION_MAX;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
				.sessionManagement().maximumSessions(SESSION_MAX)
				.sessionRegistry(sessionRegistry())
				.maxSessionsPreventsLogin(false)
				.expiredUrl("/api/auth/expire")
				.and()
				.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
			
			.and()
				.logout().permitAll().and().authorizeRequests()
				.antMatchers("/"
							,"/api/auth/check-license**/*"
							,"/api/parent/register**"
							,"/login"
							,"/register"
							,"/resources/**"
							,"/jsMsgSource*")
					.permitAll()
				.anyRequest().authenticated()
			.and()
				.httpBasic()
			.and()
				 .formLogin()
				 .loginPage("/login")
				 .usernameParameter("userName")
				 .passwordParameter("password")
				 .successHandler(successHandler());
	}

	@Bean
	public AuthenticationSuccessHandler successHandler(){
		AuthenticationSuccessHandler successHandler = new AuthenticationSuccessHandler();
		successHandler.setDefaultTargetUrl("/account/search");
		return successHandler;
	}
	
	@Bean
	public SessionService sessionService() {
		return new RedisSessionService();
//		return new WebSessionService();
	}

	@Bean
	public SessionRegistry sessionRegistry() {
//		return new SpringSessionBackedSessionRegistry(this.sessionRepository);
		return new SessionRegistryImpl();
	}
	
//	@Bean
//	public MapSessionRepository sessionRepository() {
//		return new MapSessionRepository();
//	}

	@Bean
	public LettuceConnectionFactory connectionFactory() {
		LettuceConnectionFactory redisConnectionFactory = new LettuceConnectionFactory();
		redisConnectionFactory.setHostName(REDIS_HOST);
		redisConnectionFactory.setPort(REDIS_PORT);
		return redisConnectionFactory;
	}
	
//	@Bean
//	public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory cf) {
//		RedisTemplate<String, String> redisTemplate = new RedisTemplate<String, String>();
//		redisTemplate.setConnectionFactory(cf);
//		return redisTemplate;
//	}
	
	@Bean
	public CacheManager cacheManager(RedisTemplate redisTemplate) {
		RedisCacheManager cacheManager = new RedisCacheManager(redisTemplate);
		cacheManager.setDefaultExpiration(60*60);
		return cacheManager;
	}

	@Bean
	public KeyGenerator keyGenerator() {
		return new KeyGenerator() {
			@Override
			public Object generate(Object target, Method method, Object... params){
				StringBuilder sb = new StringBuilder();
				sb.append(target.getClass().getName());
				sb.append(method.getName());
				for (Object obj : params) {
					sb.append(obj.toString());
				}
				return sb.toString();
			}
		};
	}

}
