package com.athena.example.springbootextension.annotation;

import com.athena.example.springbootextension.config.EnableAthenaConfig;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @auther: yusheng
 */
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import(EnableAthenaConfig.class)
public @interface EnableAthena {
    String value() default "";
}
