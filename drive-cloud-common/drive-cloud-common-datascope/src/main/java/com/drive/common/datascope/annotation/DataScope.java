package com.drive.common.datascope.annotation;

import java.lang.annotation.*;

/**
 * 数据权限过滤注解
 *
 * @author xiaoguo
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DataScope {
    /**
     * 部门表的别名
     */
    String deptAlias() default "";

    /**
     * 用户表的别名
     */
    String userAlias() default "";

    /**
     * 模块
     * @return
     */
    String module() default "";
}
