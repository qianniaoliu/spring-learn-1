package com.athena.example.rsocket;

import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

/**
 * @author yusheng
 */
@Service
public class MyService {

    private final Mono<RSocketRequester> rSocketRequester;

    public MyService(RSocketRequester.Builder builder) {
        this.rSocketRequester = builder.connectTcp("localhost", 9898);
    }

    public Mono<User> someRSocketCall(String name){
        return this.rSocketRequester.flatMap(req ->
                req.route("user.get").data("name").retrieveMono(User.class));
    }
}
