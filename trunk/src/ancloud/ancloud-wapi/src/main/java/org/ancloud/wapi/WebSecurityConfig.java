package org.ancloud.wapi;

import javax.inject.Inject;

import org.ancloud.fw.core.service.SessionService;
import org.ancloud.fw.presentation.security.authentication.listener.LoginAttemptFilter;
import org.ancloud.fw.tracking.ClientAddressMDCFilter;
import org.ancloud.fw.tracking.PrincipalMDCFilter;
import org.ancloud.fw.tracking.TraceHttpServletRequestFilter;
import org.ancloud.presentation.service.RedisSessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.web.ErrorAttributes;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.session.ExpiringSession;
import org.springframework.session.FindByIndexNameSessionRepository;
import org.springframework.session.config.annotation.web.http.EnableSpringHttpSession;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.session.security.SpringSessionBackedSessionRegistry;
import org.springframework.web.filter.DelegatingFilterProxy;

@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
@EnableRedisHttpSession(maxInactiveIntervalInSeconds = -1)
//@EnableSpringHttpSession
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Value("${spring.redis.host}")
	private String REDIS_HOST;

	@Value("${spring.redis.port}")
	private int REDIS_PORT;

	@Inject
	UserDetailsService userDetailsService;

	@Value("${security.session.max}")
	private int SESSION_MAX;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.csrf().disable()
			.exceptionHandling()
			.and()
				.sessionManagement().maximumSessions(SESSION_MAX)
				.sessionRegistry(sessionRegistry())
				.maxSessionsPreventsLogin(false)
				.expiredUrl("/api/auth/expire")
				.and()
				.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
			.and()
				// .antMatcher("/api/auth")
				.httpBasic()
				// .and()
				// .formLogin()
				// .loginPage("/api/login").permitAll()
			.and()
				.logout().permitAll().and().authorizeRequests()
				.antMatchers("/api/auth/check-license**/*","/api/parent/register**").permitAll()
				.anyRequest().authenticated()
			.and()
			.addFilterBefore(traceServletRequestFilter(), BasicAuthenticationFilter.class)
			.addFilterBefore(loginAttemptFilter(), BasicAuthenticationFilter.class);
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		// auth.inMemoryAuthentication()
		// .withUser("user")
		// .password("password")
		// .roles("USER");
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		return passwordEncoder;
	}

	@Bean
	public ErrorAttributes errorAttributes() {
		return new CustomErrorAttributes();
	}

	// @Bean
	// public AuthenticationTokenFilter authenticationTokenFilter(){
	// return new AuthenticationTokenFilter();
	// }

	// @Bean
	// public MapSessionRepository sessionRepository() {
	// return new MapSessionRepository();
	// }

	@Bean
	public LettuceConnectionFactory connectionFactory() {
		LettuceConnectionFactory redisConnectionFactory = new LettuceConnectionFactory();
		redisConnectionFactory.setHostName(REDIS_HOST);
		redisConnectionFactory.setPort(REDIS_PORT);
		return redisConnectionFactory;
	}

//	@Autowired
//	@Qualifier(value="sessionRepository")
//	public FindByIndexNameSessionRepository sessionRepository;

	@Bean
	public SessionRegistry sessionRegistry() {
//		return new SpringSessionBackedSessionRegistry(this.sessionRepository);
		return new SessionRegistryImpl();
	}
	
	@Bean
	public SessionService sessionService() {
		return new RedisSessionService();
	}
	
	@Bean
	@Order(Ordered.HIGHEST_PRECEDENCE)
	public TraceHttpServletRequestFilter traceServletRequestFilter() {
		TraceHttpServletRequestFilter filter = new TraceHttpServletRequestFilter();
		return filter;
	}
	
	@Bean
	public PrincipalMDCFilter principalMDCFilter() {
		PrincipalMDCFilter filter = new PrincipalMDCFilter();
		return filter;
	}
	
	@Bean
	public ClientAddressMDCFilter clientAddressMDCFilter() {
		ClientAddressMDCFilter filter = new ClientAddressMDCFilter();
		return filter;
	}
	
	@Bean
	@Order(4)
	public LoginAttemptFilter loginAttemptFilter() {
		LoginAttemptFilter filter = new LoginAttemptFilter();
		return filter;
	}
}
