package com.drive.common.datascope;

import org.springframework.context.annotation.ComponentScan;

/**
 * 引用此jar时，注入此工程中的bean
 * @author XIAOGUO
 */

@ComponentScan(value = {"com.drive.common.datascope"})
public class AutoConfiguration {

}
