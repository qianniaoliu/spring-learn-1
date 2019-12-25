package com.athena.example.extension.config.spring.xml;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

/**
 * @author yusheng
 */
public class AthenaNamespaceHandler extends NamespaceHandlerSupport {
    @Override
    public void init() {
        System.out.println("init()");
        registerBeanDefinitionParser("athena", new AthenaBeanDefinitionParser());
    }
}
