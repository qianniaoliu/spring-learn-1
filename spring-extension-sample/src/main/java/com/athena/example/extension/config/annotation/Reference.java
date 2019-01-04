package com.athena.example.extension.config.annotation;

import java.lang.annotation.*;

/**
 * @auther: yusheng
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Reference {

    String athenaBeanName() default "";
}
