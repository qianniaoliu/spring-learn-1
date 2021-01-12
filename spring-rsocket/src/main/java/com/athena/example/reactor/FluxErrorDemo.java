package com.athena.example.reactor;

import reactor.core.publisher.Flux;
import reactor.util.retry.Retry;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author yusheng
 */
public class FluxErrorDemo {

    public static void main(String[] args) {
        AtomicInteger errorCount = new AtomicInteger();
        AtomicInteger transientHelper = new AtomicInteger();
        Flux<Integer> transientFlux = Flux.<Integer>generate(sink -> {
            int i = transientHelper.getAndIncrement();
            if (i == 10) {
                sink.next(i);
                sink.complete();
            } else if (i % 3 == 0) {
                sink.next(i);
            } else {
                sink.error(new IllegalStateException("Transient error at " + i));
            }
        })
                .doOnError(e -> errorCount.incrementAndGet());

        transientFlux.retryWhen(Retry.max(2).transientErrors(true))
                .blockLast();
        System.out.println("错误统计数量:" + errorCount.get());
    }
}
