package com.athena.sample;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author yusheng
 */
public class DependencyLookupDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(User.class);
        applicationContext.refresh();

        ObjectProvider<User> userObjectProvider = applicationContext.getBeanProvider(User.class);


        applicationContext.close();

    }
}
