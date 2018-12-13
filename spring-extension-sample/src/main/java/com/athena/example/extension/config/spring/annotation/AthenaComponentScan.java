package com.athena.example.extension.config.spring.annotation;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @auther: yusheng
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(AthenaComponentScanRegistrar.class)
public @interface AthenaComponentScan {

    String[] basePackages() default {};
}
