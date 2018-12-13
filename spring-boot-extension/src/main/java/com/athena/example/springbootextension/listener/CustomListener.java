package com.athena.example.springbootextension.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

/**
 * @auther: yusheng
 */
public class CustomListener implements ApplicationListener {

    private final static Logger LOGGER = LoggerFactory.getLogger(CustomListener.class);

    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        LOGGER.error("监听到的事件为:" + event.getClass().getSimpleName());
    }
}
