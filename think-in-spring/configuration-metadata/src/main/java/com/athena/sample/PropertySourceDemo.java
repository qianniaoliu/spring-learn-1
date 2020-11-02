package com.athena.sample;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @author yusheng
 */
@PropertySource(name = "yamlPropertySource", value = {"classpath:athena.yaml"}, factory = AthenaPropertySourceFactory.class)
@Configuration
public class PropertySourceDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(PropertySourceDemo.class);
        applicationContext.refresh();
        applicationContext.close();
    }
}
