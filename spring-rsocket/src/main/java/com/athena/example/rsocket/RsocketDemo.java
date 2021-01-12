package com.athena.example.rsocket;

import reactor.core.publisher.Flux;

import java.util.stream.Stream;

/**
 * @author yusheng
 */
public class RsocketDemo {

    public static void main(String[] args) {
        Flux<String> seq1 = Flux.just("foo", "bar", "foobar");
        seq1.subscribe(item -> {
            System.out.println(item);
        }, error -> System.out.println(error.getMessage()),
                () -> System.out.println("Done"),
                sub -> sub.request(3));
    }
}
