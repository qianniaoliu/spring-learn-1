package com.athena.example.springbootextension.config;

import com.athena.example.springbootextension.annotation.ConditionOnValue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @auther: yusheng
 */
@Configuration
public class EnableAthenaConfig {


    @Bean
    @ConditionOnValue("yusheng")
    public String helloWorld(){
        return "Hello World";
    }
}
