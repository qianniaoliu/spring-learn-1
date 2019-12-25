package com.athena.example.springbootextension;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @auther: yusheng
 */

@SpringBootApplication
//@ServletComponentScan(basePackages = "com.athena.example.springlearn1.servlet")
//@EnableAthena
public class SpringBootExtensionApplication {
    private final static Logger LOGGER = LoggerFactory.getLogger(SpringBootExtensionApplication.class);
    public static void main(String[] args) {
        //		SpringApplication.run(SpringLearn1Application.class, args);

        ConfigurableApplicationContext context = new SpringApplicationBuilder(SpringBootExtensionApplication.class)
                .profiles("yusheng")
                .web(WebApplicationType.SERVLET)
                .run(args);
        String helloWorld = (String) context.getBean("helloWorld");
        LOGGER.info("helloWorld Bean:" + helloWorld);
    }
}
