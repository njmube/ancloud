package org.ancloud.wapi;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {
		@Bean
        public CookieSerializer cookieSerializer() {
                DefaultCookieSerializer serializer = new DefaultCookieSerializer();
                serializer.setCookieName("SESSIONID"); 
                serializer.setCookiePath("/"); 
                serializer.setDomainNamePattern("^.+?\\.(\\w+\\.[a-z]+)$"); 
                return serializer;
        }
}