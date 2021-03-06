package org.ancloud.wapi;

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
import org.ancloud.service.account.LoginAttemptService;
import org.ancloud.service.authentication.UserDetailsServiceImpl;
import org.aspectj.weaver.ast.And;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.web.ErrorAttributes;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.session.web.http.SessionRepositoryFilter;
import org.springframework.web.filter.RequestContextFilter;
import org.springframework.beans.factory.annotation.Autowired;

@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
@Order(90)
@EnableJpaAuditing(auditorAwareRef="auditorProvider")
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{
  
  @Inject
  ApplicationProperties applicationProperties;
  
  @Inject
  SessionRegistry sessionRegistry;
  
  @Inject
  PasswordEncoder passwordEncoder;

  @Value("${application.security.maxSession}")
  private int SESSION_MAX;
  
  
  @Bean
  protected UserDetailsService userDetailsService(){
    return new UserDetailsServiceImpl();
  }
  
  @Autowired
  public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//     auth.inMemoryAuthentication()
//     .withUser("user")
//     .password("password")
//     .roles("USER");
    auth.userDetailsService(userDetailsService())
      .passwordEncoder(passwordEncoder);
  }
  
  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.sessionManagement()
        .maximumSessions(SESSION_MAX)
        .sessionRegistry(sessionRegistry)
        .maxSessionsPreventsLogin(false)
        .expiredUrl("/api/auth/expire")
        .and()
        .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
      .and()
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
        .httpBasic();
//      .and()
//      .addFilterBefore(traceServletRequestFilter(), BasicAuthenticationFilter.class)
//      .addFilterBefore(loginAttemptFilter(), BasicAuthenticationFilter.class);

  }
  
//  @Bean
//  @Order(Ordered.HIGHEST_PRECEDENCE)
//  public TraceHttpServletRequestFilter traceServletRequestFilter() {
//    TraceHttpServletRequestFilter filter = new TraceHttpServletRequestFilter();
//    return filter;
//  }
  
  @Bean
  public FilterRegistrationBean traceServletRequestFilter() {
    FilterRegistrationBean filter = new FilterRegistrationBean(new TraceHttpServletRequestFilter());
    filter.setOrder(Ordered.HIGHEST_PRECEDENCE);
    return filter;
  }
  
//  @Bean
//  @Order(Ordered.HIGHEST_PRECEDENCE)
//  public LoginAttemptFilter loginAttemptFilter(LoginAttemptService loginAttemptService) {
//    LoginAttemptFilter filter = new LoginAttemptFilter(loginAttemptService);
//    return filter;
//  }
  
  @Bean
  public FilterRegistrationBean loginAttemptFilter(LoginAttemptService loginAttemptService) {
    FilterRegistrationBean filter = new FilterRegistrationBean(new LoginAttemptFilter(loginAttemptService));
    filter.setOrder(Ordered.HIGHEST_PRECEDENCE+1);
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
