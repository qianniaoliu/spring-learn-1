package com.athena.extensible.xml;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

/**
 * @author yusheng
 */
public class AthenaNamespaceHandler extends NamespaceHandlerSupport {

    @Override
    public void init() {
        registerBeanDefinitionParser("athena", new AthenaBeanDefinitionParser());
    }
}
