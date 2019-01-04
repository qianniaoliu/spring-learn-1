package com.athena.example.extension.config.spring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;

/**
 * @auther: yusheng
 */
public class AthenaBean implements InitializingBean {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private String name;

    private String address;


    @Override
    public void afterPropertiesSet() throws Exception {
        logger.info("初始化AthenaBean");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
