package com.athena.example.springbootextension.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContainerInitializer;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import java.util.Set;

/**
 * @auther: yusheng
 */
public class CustomServletContainerInitializer implements ServletContainerInitializer {

    private final static Logger LOGGER = LoggerFactory.getLogger(CustomServletContainerInitializer.class);

    @Override
    public void onStartup(Set<Class<?>> set, ServletContext servletContext) throws ServletException {
        LOGGER.info("自定义Servlet容器初始化完成回调!");
    }
}
