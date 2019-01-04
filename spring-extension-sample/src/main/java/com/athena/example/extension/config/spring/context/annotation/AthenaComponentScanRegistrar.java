package com.athena.example.extension.config.spring.context.annotation;

import com.athena.example.extension.config.spring.beans.factory.annotation.AthenaAnnotationBeanPostProcessor;
import com.athena.example.extension.config.spring.beans.factory.annotation.ReferenceInjectedBeanPostProcessor;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.*;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.type.AnnotationMetadata;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;


/**
 * @auther: yusheng
 */
public class AthenaComponentScanRegistrar implements ImportBeanDefinitionRegistrar {

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        Set<String> packagesToScan = getPackagesToScan(importingClassMetadata);
        registerAthenaAnnotationBeanPostProcessor(packagesToScan, registry);
        registerReferenceInjectedBeanPostProcessor(registry);
    }



    private Set<String> getPackagesToScan(AnnotationMetadata importingClassMetadata) {
        AnnotationAttributes annotationAttributes = AnnotationAttributes.fromMap(
                importingClassMetadata.getAnnotationAttributes(AthenaComponentScan.class.getName()));
        String[] basePackages = annotationAttributes.getStringArray("basePackages");

        Set<String> packagesToScan = new LinkedHashSet<>(Arrays.asList(basePackages));
        return packagesToScan;
    }


    private void registerAthenaAnnotationBeanPostProcessor(Set<String> packagesToScan, BeanDefinitionRegistry registry){
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.rootBeanDefinition(AthenaAnnotationBeanPostProcessor.class);
        beanDefinitionBuilder.setRole(BeanDefinition.ROLE_INFRASTRUCTURE);
        beanDefinitionBuilder.addConstructorArgValue(packagesToScan);
        AbstractBeanDefinition beanDefinition = beanDefinitionBuilder.getBeanDefinition();
        BeanDefinitionReaderUtils.registerWithGeneratedName(beanDefinition, registry);
    }

    private void registerReferenceInjectedBeanPostProcessor(BeanDefinitionRegistry registry) {
        // registry ReferenceInjectedBeanPostProcessor Bean

        RootBeanDefinition beanDefinition = new RootBeanDefinition(ReferenceInjectedBeanPostProcessor.class);
        beanDefinition.setRole(BeanDefinition.ROLE_INFRASTRUCTURE);
        registry.registerBeanDefinition("referenceInjectedBeanPostProcessor", beanDefinition);

    }
}
