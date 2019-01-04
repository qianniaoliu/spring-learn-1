package com.athena.example.extension.config.spring.beans.factory.annotation;

import com.athena.example.extension.config.annotation.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.annotation.InjectionMetadata;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessorAdapter;
import org.springframework.beans.factory.support.MergedBeanDefinitionPostProcessor;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.core.PriorityOrdered;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.StringUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * @auther: yusheng
 */
public class ReferenceInjectedBeanPostProcessor extends InstantiationAwareBeanPostProcessorAdapter
            implements MergedBeanDefinitionPostProcessor, PriorityOrdered, BeanFactoryAware {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final ConcurrentMap<String, ReferenceInjectedMetadata> injectionMetadataCache =
            new ConcurrentHashMap<String, ReferenceInjectedMetadata>(20);

    private BeanFactory beanFactory;



    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }

    @Override
    public void postProcessMergedBeanDefinition(RootBeanDefinition beanDefinition, Class<?> beanType, String beanName) {
    }

    @Override
    public PropertyValues postProcessProperties(PropertyValues pvs, Object bean, String beanName) throws BeansException {

        logger.info("Properties后置处理!");
        ReferenceInjectedMetadata metadata = findReferenceMetadata(beanName, bean.getClass(), pvs);
        try {
            metadata.inject(bean, beanName, pvs);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }

        return pvs;
    }

    private ReferenceInjectedMetadata findReferenceMetadata(String beanName, Class<?> clazz, PropertyValues pvs) {

        String cacheKey = StringUtils.hasLength(beanName) ? beanName : clazz.getName();
        ReferenceInjectedMetadata metadata = injectionMetadataCache.get(cacheKey);
        if(ReferenceInjectedMetadata.needsRefresh(metadata, clazz)){
            synchronized (this.injectionMetadataCache){
                metadata = injectionMetadataCache.get(cacheKey);
                if(InjectionMetadata.needsRefresh(metadata, clazz)){
                    metadata = buildReferenceMetadata(clazz);
                    injectionMetadataCache.putIfAbsent(beanName, metadata);
                }
            }
        }
        return metadata;
    }

    private ReferenceInjectedMetadata buildReferenceMetadata(Class<?> clazz) {
        Collection<InjectionMetadata.InjectedElement> fieldElements = findFieldReferenceMetadata(clazz);
        return new ReferenceInjectedMetadata(clazz, fieldElements);
    }

    private Collection<InjectionMetadata.InjectedElement> findFieldReferenceMetadata(Class<?> clazz) {
        final List<InjectionMetadata.InjectedElement> injectedElementList = new ArrayList<>();
        ReflectionUtils.doWithFields(clazz, field -> {
            Annotation annotation = AnnotationUtils.getAnnotation(field, Reference.class);

            if(annotation != null){

                if (Modifier.isStatic(field.getModifiers())) {
                    if (logger.isWarnEnabled()) {
                        logger.warn("@" + Reference.class.getName() + " is not supported on static fields: " + field);
                    }
                    return;
                }
                injectedElementList.add(new ReferenceInjectedElement(field, annotation));
            }
        });
        return injectedElementList;
    }

    @Override
    public int getOrder() {
        return 8848;
    }

    private class ReferenceInjectedMetadata extends InjectionMetadata{

        private Collection<InjectedElement> injectedElementList;

        public ReferenceInjectedMetadata(Class<?> targetClass, Collection<InjectedElement> injectedElementList) {
            super(targetClass, injectedElementList);
            this.injectedElementList = injectedElementList;
        }
    }


    private class ReferenceInjectedElement extends InjectionMetadata.InjectedElement{

        private Field field;

        private Annotation annotation;


        public ReferenceInjectedElement(Field field, Annotation annotation) {
            super(field, null);
            this.field = field;
            this.annotation = annotation;
        }

        @Override
        protected void inject(Object target, String requestingBeanName, PropertyValues pvs) throws Throwable {
            logger.info("开始注入");
            Reference reference = (Reference) annotation;
            String value = reference.athenaBeanName();
            if(StringUtils.hasLength(value)){
                ReflectionUtils.makeAccessible(field);
                field.set(target, value);
            }
        }
    }


}
