package com.drive.common.data.annotation;
 
import java.lang.annotation.*;
 
/**
 * 在RPC被调用方方法上添加@Idempotent注解用于解决幂等性问题
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface Idempotent {
 
}