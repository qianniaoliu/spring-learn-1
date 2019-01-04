package com.athena.example.extension.config.spring.beans.factory.annotation;

import com.athena.example.extension.config.annotation.Athena;
import com.athena.example.extension.config.spring.AthenaBean;
import com.athena.example.extension.config.spring.context.annotation.AthenaClassPathBeanDefinitionScanner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.BeanClassLoaderAware;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.*;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.context.annotation.AnnotationBeanNameGenerator;
import org.springframework.core.PriorityOrdered;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.util.ClassUtils;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * @auther: yusheng
 */
public class AthenaAnnotationBeanPostProcessor implements BeanDefinitionRegistryPostProcessor, EnvironmentAware,
                                            ResourceLoaderAware, BeanClassLoaderAware, PriorityOrdered {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private Environment environment;

    private ClassLoader classLoader;

    private ResourceLoader resourceLoader;


    private final Set<String> packagesToScan;

    public AthenaAnnotationBeanPostProcessor(Set<String> packagesToScan) {
        this.packagesToScan = packagesToScan;
    }

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {

        Set<String> resolvePackagesToScan = resolvePackagesToScan();

        if(!CollectionUtils.isEmpty(resolvePackagesToScan)){
            registerAthenaBeans(resolvePackagesToScan, registry);
        }

        logger.warn("Post BeanDefinition Registry");
    }




    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        logger.warn("Post BeanFactory Process");
    }

    private Set<String> resolvePackagesToScan() {
        Set<String> resolvePackagesToScan = new LinkedHashSet<>(packagesToScan.size());
        packagesToScan.stream()
                .filter(StringUtils::hasText)
                .forEach(packageToScan -> {
            resolvePackagesToScan.add(environment.resolvePlaceholders(packageToScan.trim()));
        });

        return resolvePackagesToScan;
    }

    private void registerAthenaBeans(Set<String> resolvePackagesToScan, BeanDefinitionRegistry registry) {
        AthenaClassPathBeanDefinitionScanner scanner = new AthenaClassPathBeanDefinitionScanner(registry,
                false, environment, resourceLoader);
        BeanNameGenerator beanNameGenerator = new AnnotationBeanNameGenerator();

        scanner.setBeanNameGenerator(beanNameGenerator);
        scanner.addIncludeFilter(new AnnotationTypeFilter(Athena.class));
        resolvePackagesToScan.stream()
                .forEach(packageToScan -> {
                    scanner.scan(packageToScan);
                    Set<BeanDefinitionHolder> beanDefinitionHolders =
                            findAthenaBeanDefinitionHolders(scanner, packageToScan, registry, beanNameGenerator);

                    beanDefinitionHolders.stream()
                            .forEach(beanDefinitionHolder -> {
                                registerAthenaBean(beanDefinitionHolder, registry, scanner);
                            });
        });
    }

    private void registerAthenaBean(BeanDefinitionHolder beanDefinitionHolder,
                                    BeanDefinitionRegistry registry, AthenaClassPathBeanDefinitionScanner scanner) {
        String annotateAthenaBeanName = beanDefinitionHolder.getBeanName();
        BeanDefinition beanDefinition = beanDefinitionHolder.getBeanDefinition();
        Class<?> beanClass = resolveClass(beanDefinitionHolder);
        Athena athena = AnnotationUtils.findAnnotation(beanClass, Athena.class);
        AbstractBeanDefinition abstractBeanDefinition = buildAthenaBeanDefinition(athena, annotateAthenaBeanName);
        String athenaBeanName = annotateAthenaBeanName + "AthenaBean";

        if(scanner.checkCandidate(athenaBeanName, abstractBeanDefinition)){
            registry.registerBeanDefinition(athenaBeanName, abstractBeanDefinition);
        }

    }

    private AbstractBeanDefinition buildAthenaBeanDefinition(Athena athena, String annotateAthenaBeanName) {
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.rootBeanDefinition(AthenaBean.class);
        BeanDefinition beanDefinition = beanDefinitionBuilder.getBeanDefinition();
        MutablePropertyValues propertyValues = beanDefinition.getPropertyValues();

        beanDefinitionBuilder.addPropertyValue("name", athena.name());

        beanDefinitionBuilder.addPropertyValue("address", athena.address());

        return beanDefinitionBuilder.getBeanDefinition();
    }

    private Class<?> resolveClass(BeanDefinitionHolder beanDefinitionHolder){
        String beanClassName = beanDefinitionHolder.getBeanDefinition().getBeanClassName();
        return ClassUtils.resolveClassName(beanClassName, classLoader);
    }

    private Set<BeanDefinitionHolder> findAthenaBeanDefinitionHolders(AthenaClassPathBeanDefinitionScanner scanner, String packageToScan, BeanDefinitionRegistry registry, BeanNameGenerator beanNameGenerator) {
        Set<BeanDefinition> beanDefinitions = scanner.findCandidateComponents(packageToScan);
        Set<BeanDefinitionHolder> beanDefinitionHolders = new LinkedHashSet<>(beanDefinitions.size());

        beanDefinitions.stream().forEach(beanDefinition -> {
            BeanDefinitionHolder beanDefinitionHolder = new BeanDefinitionHolder(beanDefinition,
                    beanNameGenerator.generateBeanName(beanDefinition, registry));
            beanDefinitionHolders.add(beanDefinitionHolder);
        });
        return beanDefinitionHolders;
    }

    @Override
    public int getOrder() {
        return 941025;
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    @Override
    public void setBeanClassLoader(ClassLoader classLoader) {
        this.classLoader = classLoader;
    }

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }
}
