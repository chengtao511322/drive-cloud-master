package com.drive.oss.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;

import java.time.Duration;

/**
 * minio 配置属性
 * @author DreamChan
 */
@RefreshScope
@Data
@ConfigurationProperties(prefix = "spring.minio")
public class MinioConfigurationProperties {

    private String url;

    private String accessKey;

    private String secretKey;

    private boolean secure = false;

    private String bucket;

    private String metricName;

    private Duration connectTimeout = Duration.ofSeconds(10);

    private Duration writeTimeout = Duration.ofSeconds(60);

    private Duration readTimeout = Duration.ofSeconds(10);

    private boolean checkBucket = true;

    private boolean createBucket = true;

}