package org.ancloud.boot.config;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{
	
	@Inject
	ApplicationProperties applicationProperties;
	
	@Inject
	SessionRegistry sessionRegistry;
	
	@Inject
	UserDetailsService userDetailsService;

	@Bean
	public PasswordEncoder passwordEncoder() {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		return passwordEncoder;
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//		 auth.inMemoryAuthentication()
//		 .withUser("user")
//		 .password("password")
//		 .roles("USER");
		auth.userDetailsService(userDetailsService)
			.passwordEncoder(passwordEncoder());
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.csrf().disable()
			.exceptionHandling()
			.and()
				.sessionManagement()
				.maximumSessions(applicationProperties.getSecurity().getMaxSession())
				.sessionRegistry(this.sessionRegistry)
				.maxSessionsPreventsLogin(false)
				.expiredUrl(applicationProperties.getSecurity().getSessionExpiredUrl())
				.and()
				.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
			.and()
				// .antMatcher("/api/auth")
				.httpBasic()
				// .and()
				// .formLogin()
				// .loginPage("/api/login").permitAll()
			.and()
				.logout().permitAll()
			.and()
				.authorizeRequests()
				.antMatchers(applicationProperties.getSecurity().getPermitAlls())
					.permitAll()
				.anyRequest().authenticated();
			
	}
}
