package com.athena.extensible.xml;

import com.athena.simple.EchoService;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.AbstractSimpleBeanDefinitionParser;
import org.w3c.dom.Element;

/**
 * @author yusheng
 */
public class AthenaBeanDefinitionParser extends AbstractSimpleBeanDefinitionParser {
    @Override
    protected void postProcess(BeanDefinitionBuilder beanDefinition, Element element) {
        super.postProcess(beanDefinition, element);
    }

    @Override
    protected Class<?> getBeanClass(Element element) {
        return EchoService.class;
    }
}
