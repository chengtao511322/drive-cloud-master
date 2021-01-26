package com.drive.common.data.config;

import com.drive.common.data.converter.CustomLongConverter;
import com.drive.common.data.jackson.RestJavaTimeModule;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * web 配置
 *
 * @author DreamChan
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {


	/** 默认日期时间格式 */
	public static final String DEFAULT_DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";


	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();
		builder.serializationInclusion(JsonInclude.Include.NON_NULL);
		builder.indentOutput(true).dateFormat(new SimpleDateFormat(DEFAULT_DATE_TIME_FORMAT));
		builder.serializerByType(Long.class, new CustomLongConverter());
		builder.serializerByType(Long.TYPE, new CustomLongConverter());

	 	builder.modules(new RestJavaTimeModule());

		converters.add(0, new MappingJackson2HttpMessageConverter(builder.build()));
	}

}
