package com.athena.example.rsocket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
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


    @MessageMapping("user")
    public User getUser(@DestinationVariable String name){
        User user = new User();
        user.setId(110);
        user.setName(name);
        return user;
    }

    @GetMapping("/remote")
    public User getRemote(){
        myService.someRSocketCall("athena").doOnNext(user -> {
            System.out.printf("lala" + user.getId());
        }).subscribe();
        return null;
    }

}
