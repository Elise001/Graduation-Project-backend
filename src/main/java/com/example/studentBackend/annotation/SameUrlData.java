package com.example.studentBackend.annotation;

import java.lang.annotation.*;

/**
 * 防止恶意请求提交注解
 * 在设置的时间内，若同一请求多次请求了，后续的请求会报错
 * @date 2025年1月30日
 */
@Inherited
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SameUrlData {
    /**
     * 接口限制时间
     * 秒
     */
    int value() default 1;
}
