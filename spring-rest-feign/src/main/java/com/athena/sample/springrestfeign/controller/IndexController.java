package com.athena.sample.springrestfeign.controller;

import com.athena.sample.springrestfeign.jaxrs.RestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @auther: yusheng
 */
@RestController(value = "/mvc")
public class IndexController {

    @Autowired
    private RestTemplate restTemplate;


    @Autowired
    private RestService restService;

    @GetMapping("/index")
    public String index(@RequestParam("message") String message){
        return restService.sayHello(message);
    }

}
