package com.athena.sample.springnacossample;

import com.alibaba.nacos.api.config.annotation.NacosValue;
import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySource;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @auther: yusheng
 */
@SpringBootApplication
public class SpringNacosSampleBootstrap {

    public static void main(String[] args) {
        new SpringApplicationBuilder(SpringNacosSampleBootstrap.class)
                .run(args);
    }

    @RestController
    public class NacosController{

        @NacosValue(value = "${athena.name}")
        private String athenaName;


        @GetMapping(value = "/get/nacos")
        String getNacos(){
            return athenaName;
        }
    }

    @NacosPropertySource(dataId = "application.properties", autoRefreshed = true)
    @Configuration
    public class NacosPropertSource{

    }
}
