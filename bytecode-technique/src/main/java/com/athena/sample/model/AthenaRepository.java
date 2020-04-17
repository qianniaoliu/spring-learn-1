package com.athena.sample.model;

import org.springframework.stereotype.Repository;

import java.lang.annotation.*;

/**
 * @author yusheng
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Repository
public @interface AthenaRepository {

    String name () default "";
}
