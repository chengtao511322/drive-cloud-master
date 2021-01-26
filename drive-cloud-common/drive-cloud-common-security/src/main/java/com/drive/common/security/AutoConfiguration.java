package com.drive.common.security;

import org.springframework.context.annotation.ComponentScan;

/**
 * 引用此jar时，注入此工程中的bean
 * @author DreamChan
 */
@ComponentScan(value = {"com.drive.common.security"})
public class AutoConfiguration {

}
