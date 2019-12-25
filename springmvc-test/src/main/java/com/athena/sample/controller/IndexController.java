package com.athena.sample.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yusheng
 */
@RestController
public class IndexController {


    @GetMapping(value = "/index")
    public String index(){
        return "index";
    }
}
