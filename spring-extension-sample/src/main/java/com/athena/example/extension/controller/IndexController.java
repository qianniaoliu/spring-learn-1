package com.athena.example.extension.controller;

import com.athena.example.extension.config.annotation.Reference;
import com.athena.example.extension.config.spring.AthenaBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @auther: yusheng
 */
@RestController
public class IndexController implements ApplicationListener {

    @Autowired
    private AthenaBean athenaBean;

    @Reference(athenaBeanName = "indexCustomYu")
    public String beanName;

    @Value("${spring.athena.en.name}")
    private String athenaName;


    @GetMapping("/get/address")
    public String index(){
        synchronized (IndexController.class){
            System.out.println(11111);
        }
        return beanName;
    }

    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        System.out.printf("11");
    }
}
