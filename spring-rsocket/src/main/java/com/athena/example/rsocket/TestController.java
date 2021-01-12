package com.athena.example.rsocket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yusheng
 */
@RestController
public class TestController {

    @Autowired
    private MyService myService;


    @MessageMapping("user.get")
    public User getUser(String name){
        User user = new User();
        user.setId(110);
        user.setName(name);
        return user;
    }

    @GetMapping("/remote")
    public User getRemote(){
        return myService.someRSocketCall("athena").block();
    }

}
