package com.drive.common.log.annotation;


import com.drive.common.core.enums.EventLogEnum;

import java.lang.annotation.*;

/**
 * 操作日志注解
 *
 * @author  xiaoguo
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface EventLog {
    /**
     * 日志记录信息
     */
    String message() default "";

    /**
     * 功能
     */
    EventLogEnum businessType() default EventLogEnum.QUERY;
}
