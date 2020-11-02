package com.athena.sample;

/**
 * @author yusheng
 */
public class WorldEchoService implements EchoService {
    @Override
    public String echo() {
        return "World";
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
