package org.medtech.wui;

import javax.inject.Inject;

import org.medtech.fw.core.service.SessionService;
import org.medtech.fw.presentation.security.authentication.listener.LoginAttemptFilter;
import org.medtech.fw.tracking.ClientAddressMDCFilter;
import org.medtech.fw.tracking.PrincipalMDCFilter;
import org.medtech.fw.tracking.TraceHttpServletRequestFilter;
import org.medtech.presentation.service.WebSessionService;
import org.medtech.service.authentication.AuthenticationSuccessHandler;
import org.medtech.wui.controller.CustomErrorAttributes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.web.ErrorAttributes;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
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
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.session.MapSessionRepository;
import org.springframework.session.config.annotation.web.http.EnableSpringHttpSession;
import org.springframework.session.web.http.SessionRepositoryFilter;
import org.springframework.web.filter.RequestContextFilter;

@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
//@EnableRedisHttpSession(maxInactiveIntervalInSeconds = -1)
@EnableSpringHttpSession
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Value("${spring.redis.host}")
	private String REDIS_HOST;

	@Value("${spring.redis.port}")
	private int REDIS_PORT;

	@Inject
	UserDetailsService userDetailsService;

	@Value("${medtech.security.session.max}")
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
				 .successHandler(successHandler())
			.and()
			.addFilterBefore(traceServletRequestFilter(), BasicAuthenticationFilter.class)
			.addFilterBefore(loginAttemptFilter(), BasicAuthenticationFilter.class);
	}

	@Bean
	public AuthenticationSuccessHandler successHandler(){
		AuthenticationSuccessHandler successHandler = new AuthenticationSuccessHandler();
		successHandler.setDefaultTargetUrl("/account/search");
		return successHandler;
	}
	
	@Bean
	@ConditionalOnMissingBean(RequestContextFilter.class)
	public FilterRegistrationBean requestContextFilter(){
		FilterRegistrationBean requestContextFilter = new FilterRegistrationBean();
		requestContextFilter.setFilter(new RequestContextFilter());
		requestContextFilter.setOrder(SessionRepositoryFilter.DEFAULT_ORDER+1);
		return requestContextFilter;
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

	@Bean
	public SessionService sessionService() {
//		return new RedisSessionService();
		return new WebSessionService();
	}

	@Bean
	public SessionRegistry sessionRegistry() {
//		return new SpringSessionBackedSessionRegistry(this.sessionRepository);
		return new SessionRegistryImpl();
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
	
	@Bean
	public MapSessionRepository sessionRepository() {
		return new MapSessionRepository();
	}

//	@Bean
//	public LettuceConnectionFactory connectionFactory() {
//		LettuceConnectionFactory redisConnectionFactory = new LettuceConnectionFactory();
//		redisConnectionFactory.setHostName(REDIS_HOST);
//		redisConnectionFactory.setPort(REDIS_PORT);
//		return redisConnectionFactory;
//	}
}
