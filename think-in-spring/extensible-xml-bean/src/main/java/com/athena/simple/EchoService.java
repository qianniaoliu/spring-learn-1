package com.athena.simple;

/**
 * @author yusheng
 */
public class EchoService {

    private String className;

    private String alias;

    private String version;

    public void hello(){
        System.out.println("Hello,World");
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
