package com.athena.sample;

/**
 * @author yusheng
 */
public interface EchoService {

    String echo();

    String echoByParam(String param);

    void noRes(String param);
}
