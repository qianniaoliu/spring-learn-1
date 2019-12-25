package com.athena.example.springbootextension.config;

import com.athena.example.springbootextension.endpoint.AthenaEndpoint;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @auther: yusheng
 */
@Configuration
public class AthenaEndpointConfig {


    @Bean
    public AthenaEndpoint athenaEndpoint(){
        return new AthenaEndpoint();
    }
}
