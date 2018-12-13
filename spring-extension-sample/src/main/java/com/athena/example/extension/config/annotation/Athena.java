package com.athena.example.extension.config.annotation;

import java.lang.annotation.*;

/**
 * @auther: yusheng
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Athena {

    String name() default "";

    String address() default "";
}
