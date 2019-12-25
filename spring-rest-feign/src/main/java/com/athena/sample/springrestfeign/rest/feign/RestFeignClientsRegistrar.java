package com.athena.sample.springrestfeign.rest.feign;

import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.support.BeanDefinitionReaderUtils;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.util.ClassUtils;

import java.util.Map;
import java.util.stream.Stream;

/**
 * @auther: yusheng
 */
public class RestFeignClientsRegistrar implements ImportBeanDefinitionRegistrar {
    @Override
    public void registerBeanDefinitions(AnnotationMetadata annotationMetadata, BeanDefinitionRegistry registry) {
        Map<String, Object> metadata = annotationMetadata.getAnnotationAttributes(EnableRestFeignClients.class.getName());
        Class<?>[] clientClasses = (Class<?>[]) metadata.get("clients");
        Stream.of(clientClasses)
                .forEach(clientClazz -> {

                    registerRestFeignClientFactoryBean(clientClazz, registry);

                });
    }

    private void registerRestFeignClientFactoryBean(Class<?> clientClazz, BeanDefinitionRegistry registry){
        RestFeignClient restFeignClient = clientClazz.getAnnotation(RestFeignClient.class);
        String serviceName = restFeignClient.value();
        RestFeignClientFactoryBean factoryBean = new RestFeignClientFactoryBean();
        GenericBeanDefinition bd = new GenericBeanDefinition();
        bd.getPropertyValues().addPropertyValue("serviceName", serviceName);
        bd.getPropertyValues().addPropertyValue("clientClazz", clientClazz);
        bd.setBeanClass(factoryBean.getClass());
        BeanDefinitionHolder holder =
                new BeanDefinitionHolder(bd, BeanDefinitionReaderUtils.generateBeanName(bd, registry));
        BeanDefinitionReaderUtils.registerBeanDefinition(holder, registry);
    }

}
