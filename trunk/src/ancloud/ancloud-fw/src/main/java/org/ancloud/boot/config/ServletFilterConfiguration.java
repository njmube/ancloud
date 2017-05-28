package org.ancloud.boot.config;

import org.ancloud.fw.presentation.authentication.LoginAttemptFilter;
import org.ancloud.fw.presentation.tracking.ClientAddressMDCFilter;
import org.ancloud.fw.presentation.tracking.PrincipalMDCFilter;
import org.ancloud.fw.presentation.tracking.TraceHttpServletRequestFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@Order(91)
public class ServletFilterConfiguration extends WebSecurityConfigurerAdapter{
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.addFilterBefore(traceServletRequestFilter(), BasicAuthenticationFilter.class)
			.addFilterBefore(loginAttemptFilter(), BasicAuthenticationFilter.class);
	}
	@Bean
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
	public LoginAttemptFilter loginAttemptFilter() {
		LoginAttemptFilter filter = new LoginAttemptFilter();
		return filter;
	}
	
	
	
}
