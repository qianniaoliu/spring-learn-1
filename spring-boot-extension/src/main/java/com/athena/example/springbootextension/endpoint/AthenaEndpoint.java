package com.athena.example.springbootextension.endpoint;

import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;

import java.util.HashMap;
import java.util.Map;

/**
 * @auther: yusheng
 */
@Endpoint(id = "athena-endpoint")
public class AthenaEndpoint {

    @ReadOperation
    public Map<String, Object> read(){
        Map<String, Object> maps = new HashMap<>();
        maps.put("athena", "xiaomage");
        return maps;
    }
}
