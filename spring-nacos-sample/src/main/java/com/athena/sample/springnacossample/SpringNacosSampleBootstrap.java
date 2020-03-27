package com.athena.sample.springnacossample;

import com.alibaba.nacos.api.annotation.NacosInjected;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.config.annotation.NacosValue;
import com.alibaba.nacos.api.naming.NamingService;
import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySource;
import com.alibaba.nacos.spring.context.annotation.discovery.EnableNacosDiscovery;
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

        @NacosValue(value = "${athena.name}", autoRefreshed = true)
        private String athenaName;

        @NacosInjected
        private ConfigService configService;


        @GetMapping(value = "/get/nacos")
        String getNacos(){
            return athenaName;
        }

    }

    @NacosPropertySource(dataId = "nacos-config-sample", autoRefreshed = true)
    @Configuration
    public class NacosPropertSource{

    }
}
