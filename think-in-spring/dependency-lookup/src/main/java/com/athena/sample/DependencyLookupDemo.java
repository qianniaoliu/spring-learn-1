package com.athena.sample;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author yusheng
 */
public class DependencyLookupDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(DefaultEchoService.class);
        applicationContext.refresh();
        ObjectProvider<TestService> userObjectProvider = applicationContext.getBeanProvider(TestService.class);
        ObjectProvider<EchoService> userObjectProvider1 = applicationContext.getBeanProvider(EchoService.class);
        applicationContext.close();

    }
}
