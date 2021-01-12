package com.athena.example.reactor;

import org.reactivestreams.Subscription;
import reactor.core.publisher.BaseSubscriber;

/**
 * @author yusheng
 */
public class SimpleSubscriber extends BaseSubscriber<String> {

    @Override
    protected void hookOnSubscribe(Subscription subscription) {
        System.out.println("Subscribed");
        request(1);
    }

    @Override
    protected void hookOnNext(String value) {
        System.out.println("元素:" + value);
//        requestUnbounded();
        cancel();
//        request(1);
    }
}
