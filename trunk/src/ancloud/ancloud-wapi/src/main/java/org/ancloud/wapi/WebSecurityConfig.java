package org.ancloud.wapi;

import org.springframework.boot.autoconfigure.web.ErrorAttributes;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration

public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Bean
	public ErrorAttributes errorAttributes() {
		return new CustomErrorAttributes();
	}
}
