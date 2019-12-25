package com.athena.sample.springrestfeign.rest.feign;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @auther: yusheng
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Import(RestFeignClientsRegistrar.class)
public @interface EnableRestFeignClients {

    Class<?>[] clients() default {};
}
