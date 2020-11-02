package com.athena.simple;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;

/**
 * @author yusheng
 */
public class ExtensibleBeanDefinitionDemo {

    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
//        reader.loadBeanDefinitions("spring-athena.xml");
        beanFactory.registerSingleton("exhoService", new EchoService());
        EchoService echoService = beanFactory.getBean(EchoService.class);
        echoService.hello();
    }
}
