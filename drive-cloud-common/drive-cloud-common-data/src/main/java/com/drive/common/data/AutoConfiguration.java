package com.drive.common.data;

import org.springframework.context.annotation.ComponentScan;

/**
 * 引用此jar时，注入此工程中的bean
 * @author DreamChan
 */
@ComponentScan(value = {"com.drive.common.data"})
public class AutoConfiguration {
}
