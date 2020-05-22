package com.athena.sample;

/**
 * @author yusheng
 */
public class HelloEchoService implements EchoService {
    @Override
    public String echo() {
        return "Hello";
    }

    @Override
    public String echoByParam(String param) {
        return param;
    }

    @Override
    public void noRes(String param) {
        System.out.println(param);
    }
}
