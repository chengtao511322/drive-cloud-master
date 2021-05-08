package com.drive.common.log;

import org.springframework.context.annotation.ComponentScan;

/**
 * 引用此jar时，注入此工程中的bean
 * @author xiaoguo
 */
@ComponentScan(value = {"com.drive.common.log"})
public class AutoConfiguration {

}
