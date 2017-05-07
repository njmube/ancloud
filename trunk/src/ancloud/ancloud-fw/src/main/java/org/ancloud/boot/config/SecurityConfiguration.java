package org.ancloud.boot.config;

import javax.inject.Inject;

import org.ancloud.boot.config.properties.ApplicationProperties;
import org.ancloud.fw.presentation.authentication.LoginAttemptFilter;
import org.ancloud.fw.presentation.tracking.ClientAddressMDCFilter;
import org.ancloud.fw.presentation.tracking.PrincipalMDCFilter;
import org.ancloud.fw.presentation.tracking.TraceHttpServletRequestFilter;
import org.ancloud.presentation.context.AccountAuditorAware;
import org.ancloud.presentation.context.CustomErrorAttributes;
import org.ancloud.presentation.service.SsePushService;
import org.ancloud.presentation.service.SsePushServiceImpl;
import org.ancloud.service.authentication.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.web.ErrorAttributes;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.session.web.http.SessionRepositoryFilter;
import org.springframework.web.filter.RequestContextFilter;

@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{
	
	@Inject
	ApplicationProperties applicationProperties;
	
	@Inject
	SessionRegistry sessionRegistry;
	
	@Inject
	PasswordEncoder passwordEncoder;

	@Bean
	protected UserDetailsService userDetailsService(){
		return new UserDetailsServiceImpl();
	}
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//		 auth.inMemoryAuthentication()
//		 .withUser("user")
//		 .password("password")
//		 .roles("USER");
		auth.userDetailsService(userDetailsService())
			.passwordEncoder(passwordEncoder);
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.csrf().disable()
			.exceptionHandling()
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
			.and()
			.addFilterBefore(traceServletRequestFilter(), BasicAuthenticationFilter.class)
			.addFilterBefore(loginAttemptFilter(), BasicAuthenticationFilter.class);
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
	AccountAuditorAware auditorProvider() {
		return new AccountAuditorAware();
	}
	
	@Bean
	public ErrorAttributes errorAttributes() {
		return new CustomErrorAttributes();
	}
	@Bean
	@ConditionalOnMissingBean(RequestContextFilter.class)
	public FilterRegistrationBean requestContextFilter(){
		FilterRegistrationBean requestContextFilter = new FilterRegistrationBean();
		requestContextFilter.setFilter(new RequestContextFilter());
		requestContextFilter.setOrder(SessionRepositoryFilter.DEFAULT_ORDER+1);
		return requestContextFilter;
	}
	@Bean
	public SsePushService pushService(){
		return new SsePushServiceImpl();
	}
}
