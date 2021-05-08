package com.drive.common.redis;

import org.springframework.context.annotation.ComponentScan;

/**
 * 引用此jar时，注入此工程中的bean
 * @author xiaoguo
 */
@ComponentScan(value = {"com.drive.common.redis"})
public class AutoConfiguration {

}
