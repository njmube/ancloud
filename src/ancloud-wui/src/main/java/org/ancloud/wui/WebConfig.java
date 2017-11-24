package org.ancloud.wui;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

import org.ancloud.domain.constant.SystemConstant;
import org.ancloud.fw.core.joda.DateTimeDeserializer;
import org.ancloud.fw.core.joda.DateTimeSerializer;
import org.joda.time.DateTime;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.BeanNameViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesViewResolver;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.joda.JodaModule;

@Configuration
@EnableWebMvc
public class WebConfig extends WebMvcConfigurerAdapter {

	@Override
	public void addResourceHandlers(final ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/img/**").addResourceLocations("classpath:resources/img/")
				.setCachePeriod(60 * 60 * 24 * 365);
		registry.addResourceHandler("/resources/fonts/**").addResourceLocations("classpath:resources/fonts/")
				.setCachePeriod(60 * 60 * 24 * 365);
		registry.addResourceHandler("/resources/qr/**").addResourceLocations("file:"+SystemConstant.PATH_QR_CODE+File.separator)
		.setCachePeriod(60 * 60 * 24 * 365);
		registry.addResourceHandler("/resources/**").addResourceLocations("classpath:resources/");

	}

	@Bean
	public BeanNameViewResolver beanNameViewResolver() {
		BeanNameViewResolver resolver = new BeanNameViewResolver();
		return resolver;
	}

	@Bean
	public TilesConfigurer tilesConfigurer() {
		final TilesConfigurer configurer = new TilesConfigurer();
		configurer.setDefinitions(new String[] { "classpath:WEB-INF/tiles-definitions.xml" });
		configurer.setCheckRefresh(true);
		return configurer;
	}

	@Bean
	public TilesViewResolver tilesViewResolver() {
		TilesViewResolver resolver = new TilesViewResolver();
		return resolver;
	}

	@Bean
	public InternalResourceViewResolver defaultViewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setViewClass(JstlView.class);
		resolver.setPrefix("/WEB-INF/views/");
		resolver.setSuffix(".jsp");
		return resolver;
	}

	@Override
	public void configurePathMatch(PathMatchConfigurer configurer) {
		super.configurePathMatch(configurer);
		configurer.setUseRegisteredSuffixPatternMatch(false);
		configurer.setUseSuffixPatternMatch(false);
	}


}