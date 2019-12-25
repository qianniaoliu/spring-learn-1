package com.athena.sample.springrestfeign.rest.feign;

import java.lang.annotation.*;

/**
 * @auther: yusheng
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
public @interface RestFeignClient {

    String value() default "";

}
