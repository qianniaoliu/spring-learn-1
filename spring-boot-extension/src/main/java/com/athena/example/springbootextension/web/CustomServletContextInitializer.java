package com.athena.example.springbootextension.web;

import com.athena.example.springbootextension.servlet.AsyncServlet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.stereotype.Component;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

/**
 * @auther: yusheng
 */
@Component
public class CustomServletContextInitializer implements ServletContextInitializer {

    private final static Logger LOGGER  = LoggerFactory.getLogger(CustomServletContextInitializer.class);

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        ServletRegistrationBean registrationBean = new ServletRegistrationBean(new AsyncServlet(),"/asyncServlet");
        LOGGER.info("自定义注册Servlet！");
    }
}
