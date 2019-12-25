package com.athena.sample.springrestfeign.jaxrs;

import com.athena.sample.springrestfeign.rest.feign.RestFeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @auther: yusheng
 */
@RestFeignClient(value = "http://127.0.0.1:8080")
public interface RestService {

    @GetMapping(value = "/app/rest/index")
    String sayHello(@RequestParam("message") String message);
}
