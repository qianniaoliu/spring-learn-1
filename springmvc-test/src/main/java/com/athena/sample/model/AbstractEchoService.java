package com.athena.sample.model;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author yusheng
 */
public abstract class AbstractEchoService {

    @Autowired
    private User user;

    public abstract void hello();

    protected User getUser(){
        return this.user;
    }
}
