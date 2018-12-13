package com.athena.example.springbootextension.register;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.stereotype.Component;

/**
 * @auther: yusheng
 */
@Component
public class CustomImportBeanDefinition implements ImportBeanDefinitionRegistrar {

    private final static Logger LOGGER = LoggerFactory.getLogger(CustomImportBeanDefinition.class);

    @Override
    public void registerBeanDefinitions(AnnotationMetadata annotationMetadata, BeanDefinitionRegistry beanDefinitionRegistry) {
        LOGGER.warn("自定义bean注册");
    }
}
