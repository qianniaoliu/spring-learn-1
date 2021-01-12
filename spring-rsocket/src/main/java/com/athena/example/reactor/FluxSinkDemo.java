package com.athena.example.reactor;

import reactor.core.publisher.BaseSubscriber;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;

/**
 * @author yusheng
 */
public class FluxSinkDemo {
    public static void main(String[] args) {
        Flux<String> flux = Flux.generate(() -> 0,
                (state, sink) -> {
                    sink.next("3 x " + state + " = " + 3 * state);
                    if (state == 10) {
                        sink.complete();
                    }
                    return state + 1;
                }, state -> System.out.println(state));
        flux.subscribe(new BaseSubscriber<String>() {
            @Override
            protected void hookOnNext(String value) {
                System.out.println(value);
            }

            @Override
            protected void hookOnComplete() {
                System.out.println("Done");
            }
        });

    }
}
