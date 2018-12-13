package com.athena.example.springbootextension.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.WebApplicationInitializer;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

/**
 * @auther: yusheng
 */
public class CustomWebApplicationInitializer implements WebApplicationInitializer {

    private final static Logger LOGGER = LoggerFactory.getLogger(CustomWebApplicationInitializer.class);

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        LOGGER.info("自定义Spring-Servlet容器时回调!");
    }
}
