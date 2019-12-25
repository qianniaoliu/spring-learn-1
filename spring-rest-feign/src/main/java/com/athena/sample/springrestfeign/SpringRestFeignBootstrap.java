package com.athena.sample.springrestfeign;

import com.athena.sample.springrestfeign.jaxrs.RestService;
import com.athena.sample.springrestfeign.rest.feign.EnableRestFeignClients;
import com.athena.sample.springrestfeign.rest.feign.RestClientHttpRequestInterceptor;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * @auther: yusheng
 */
@SpringBootApplication
@EnableRestFeignClients(clients = {RestService.class})
@ServletComponentScan(basePackages = "com.athena.sample.springrestfeign.jaxrs")
public class SpringRestFeignBootstrap {

    public static void main(String[] args) {
        new SpringApplicationBuilder(SpringRestFeignBootstrap.class)
                .run(args);
    }


    @Bean
    public RestTemplate restTemplate(){
        RestTemplate restTemplate = new RestTemplate();

        List<ClientHttpRequestInterceptor> interceptors = new ArrayList<>();
        interceptors.add(new RestClientHttpRequestInterceptor());
        restTemplate.setInterceptors(interceptors);
        return restTemplate;
    }

}
