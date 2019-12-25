package com.athena.sample.springnacossample.controller;

import com.athena.sample.springnacossample.config.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Properties;

/**
 * @author yusheng
 */
@RestController
public class IndexController {

    @PostMapping(value = "/converter/custom", consumes = "application/properties")
    public String index(@RequestBody Properties properties){
        List<Long> ids = (List<Long>) properties.get("ids");
        return "xxx";
    }

    @GetMapping(value = "/index1")
    public String index1(@RequestBody User user){
        return "index";
    }
}
