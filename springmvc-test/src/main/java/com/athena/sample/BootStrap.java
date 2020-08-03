package com.athena.sample;

import com.athena.sample.model.DefaultEchoServiceImpl;
import com.athena.sample.model.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.context.annotation.RequestScope;

/**
 * @author yusheng
 */
@SpringBootApplication
public class BootStrap {

    public static void main(String[] args) {
        ApplicationContext applicationContext = SpringApplication.run(BootStrap.class, args);
        DefaultEchoServiceImpl defaultEchoService = applicationContext.getBean("defaultEchoServiceImpl", DefaultEchoServiceImpl.class);
        defaultEchoService.hello();
    }

    @Bean
//    @RequestScope
    public User user(){
        User user = new User();
        user.setId(2);
        user.setName("athena");
        return user;
    }
}
