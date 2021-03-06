package com.drive.common.data.config;

import com.drive.common.data.converter.CustomLongConverter;
import com.drive.common.data.jackson.RestJavaTimeModule;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;


/**
 * JacksonConfig
 *
 * @author xiaoguo
 */
@Configuration
@AutoConfigureBefore(JacksonAutoConfiguration.class)
public class JacksonConfiguration {

	/** 默认日期时间格式 */
	public static final String DEFAULT_DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

	@Bean
	@ConditionalOnMissingBean
	public Jackson2ObjectMapperBuilderCustomizer customizer() {
		return builder -> {
			builder.serializationInclusion(JsonInclude.Include.NON_NULL);
			builder.indentOutput(true).dateFormat(new SimpleDateFormat(DEFAULT_DATE_TIME_FORMAT));
			builder.serializerByType(Long.class, new CustomLongConverter());
			builder.serializerByType(Long.TYPE, new CustomLongConverter());
			builder.featuresToEnable(SerializationFeature.WRITE_ENUMS_USING_TO_STRING);
			builder.modules(new RestJavaTimeModule());
		};
	}


}
