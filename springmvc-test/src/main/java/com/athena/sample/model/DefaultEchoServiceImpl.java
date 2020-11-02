package com.athena.sample.model;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

/**
 * @author yusheng
 */
@Service
//@ConditionalOnProperty(name = "athena.test", havingValue = "222")
public class DefaultEchoServiceImpl extends AbstractEchoService {
    @Override
    public void hello() {
        System.out.println(getUser());
    }
}
