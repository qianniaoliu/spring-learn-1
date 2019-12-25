package com.athena.sample.springrestfeign.jaxrs;


import org.springframework.context.annotation.Configuration;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

/**
 * @auther: yusheng
 */
@ApplicationPath("/app")
@Configuration
public class RestApplication extends Application {

    private Set<Object> singletons = new HashSet<>();
    private Set<Class<?>> empty = new HashSet<>();

    public RestApplication() {
        singletons.add(new IndexJaxRs());
    }

    @Override
    public Set<Class<?>> getClasses() {
        return empty;
    }

    @Override
    public Set<Object> getSingletons() {
        return singletons;
    }
}
