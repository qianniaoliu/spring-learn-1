package com.athena.example.springbootextension.condition;

import com.athena.example.springbootextension.annotation.ConditionOnValue;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

import java.util.Map;

/**
 * @auther: yusheng
 */
public class CustomConditionOnValue implements Condition {
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        Map<String, Object> attr = metadata.getAnnotationAttributes(ConditionOnValue.class.getName());
        String profile = context.getEnvironment().getActiveProfiles()[0];
        if(attr.get("value").equals(profile)){
            return true;
        }
        return false;
    }
}
