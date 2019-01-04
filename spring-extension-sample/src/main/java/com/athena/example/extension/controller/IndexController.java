package com.athena.example.extension.controller;

import com.athena.example.extension.config.annotation.Reference;
import com.athena.example.extension.config.spring.AthenaBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @auther: yusheng
 */
@RestController
public class IndexController {

    @Autowired
    private AthenaBean athenaBean;

    @Reference(athenaBeanName = "indexCustomYu")
    public String beanName;

    @Value("${spring.athena.en.name}")
    private String athenaName;


    @GetMapping("/get/address")
    public String index(){
        return beanName;
    }
}
