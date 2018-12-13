package com.athena.example.extension.config.spring.annotation;

import com.athena.example.extension.config.spring.beans.factory.annotation.AthenaAnnotationBeanPostProcessor;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

import static org.springframework.beans.factory.support.BeanDefinitionBuilder.rootBeanDefinition;

/**
 * @auther: yusheng
 */
public class AthenaComponentScanRegistrar implements ImportBeanDefinitionRegistrar {
    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {

    }


    private void registerAthenaAnnotationBeanPostProcessor(BeanDefinitionRegistry registry){
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.rootBeanDefinition(AthenaAnnotationBeanPostProcessor.class);
        beanDefinitionBuilder.getBeanDefinition();

    }
}
