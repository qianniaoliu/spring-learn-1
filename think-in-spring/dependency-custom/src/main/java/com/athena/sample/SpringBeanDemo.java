package com.athena.sample;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author yusheng
 */
public class SpringBeanDemo {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring.xml");
        context.refresh();
        User user = context.getBean(User.class);
        System.out.println(user);
    }
}
