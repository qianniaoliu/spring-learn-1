package com.athena.sample.springrestfeign.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yusheng
 */
@RestController
public class TestController {


    @GetMapping("/test")
    public String test(){
        return "test";
    }
}
