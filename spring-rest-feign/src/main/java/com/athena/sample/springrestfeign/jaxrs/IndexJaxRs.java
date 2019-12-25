package com.athena.sample.springrestfeign.jaxrs;

import com.athena.sample.springrestfeign.jaxrs.entity.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.support.DefaultPropertySourceFactory;
import org.springframework.core.io.support.PropertySourceFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Context;
import java.util.ArrayList;
import java.util.List;

/**
 * @auther: yusheng
 */
@Path("/rest")
public class IndexJaxRs {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    public IndexJaxRs(){
        logger.info("JAX-RS: 无参构造器调用!");
    }

    /**
     * JAX-RS规范描述 : 注入Application实现类
     */
    @Context
    private Application restApplication;

    @Autowired
    private RestService restService;


    @GET
    @Path("/index")
    @Produces("application/json")
    @Consumes("application/json")
    public String index(@QueryParam("message") String message){
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(message + "-remote");
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    @POST
    @Path("/user")
    @Produces("application/json")
    @Consumes("application/json")
    public String user(User user){
        return user.getName();
    }

    @GET
    @Path("/get/{name}")
    @Produces("application/json")
    @Consumes("application/json")
    public String name(@PathParam("name") String name){
        return name;
    }

    public static void main(String[] args) {
        Class<? extends PropertySourceFactory> factoryClass = DefaultPropertySourceFactory.class;
        Boolean flag = factoryClass == PropertySourceFactory.class;
        System.out.printf(flag.toString());

        List<Integer> propertyList = new ArrayList<>();
        propertyList.add(11);
        propertyList.add(22);
        propertyList.add(0,33);
        System.out.printf("11");
    }
}
