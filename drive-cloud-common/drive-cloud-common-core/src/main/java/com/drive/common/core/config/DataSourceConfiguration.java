package com.drive.common.core.config;

import com.alibaba.druid.pool.DruidDataSource;
import io.seata.rm.datasource.DataSourceProxy;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 *  @author: haodongdong
 *  @Date: 2020/7/22 17:25
 *  @Description:  数据源代理
 */
@Configuration
public class DataSourceConfiguration {

    @Configuration
    public class DataSourceProxyConfig {
        @Bean
        @ConfigurationProperties(prefix = "spring.datasource")
        public DruidDataSource druidDataSource() {
            return new DruidDataSource();
        }

        @Primary
        @Bean
        public DataSourceProxy dataSource(DruidDataSource druidDataSource) {
            return new DataSourceProxy(druidDataSource);
        }
    }

}