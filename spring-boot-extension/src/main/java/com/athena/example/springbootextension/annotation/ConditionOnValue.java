package com.athena.example.springbootextension.annotation;

import com.athena.example.springbootextension.condition.CustomConditionOnValue;
import org.springframework.context.annotation.Conditional;

import java.lang.annotation.*;

/**
 * @auther: yusheng
 */

@Target({ ElementType.TYPE, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Conditional(CustomConditionOnValue.class)
public @interface ConditionOnValue {
    String value() default "";
}
