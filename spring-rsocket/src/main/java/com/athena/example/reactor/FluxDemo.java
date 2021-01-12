package com.athena.example.reactor;

import reactor.core.publisher.Flux;

/**
 * @author yusheng
 */
public class FluxDemo {
    public static void main(String[] args) {
        Flux<String> seq1 = Flux.just("foo", "bar", "foobar");
//        seq1.subscribe(item -> {
//                    System.out.println(item);
//                }, error -> System.out.println(error.getMessage()),
//                () -> System.out.println("Done"),
//                sub -> sub.request(3));
        seq1.limitRate(2).subscribe(new SimpleSubscriber());
    }
}
