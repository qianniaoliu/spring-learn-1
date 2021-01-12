package com.athena.example.reactor;

import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;
import reactor.core.scheduler.Schedulers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author yusheng
 */
public class FluxCreateDemo {
    private interface EventListener{
        void onDataChunk(List<String> chunk);
        void processComplete();
    }

    public static void main(String[] args) throws IOException {
        List<EventListener> eventListeners = new ArrayList<>();
        Flux.create(fluxSink -> {
            eventListeners.add(new EventListener() {
                @Override
                public void onDataChunk(List<String> chunk) {
                    System.out.println("fluxSink thread:" + Thread.currentThread().getName());
                    System.out.println("onDataChunk");
                    for(String c : chunk){
                        fluxSink.next(c);
                    }
                }
                @Override
                public void processComplete() {
                    System.out.println("processComplete");
//                    fluxSink.complete();
                }
            });
            fluxSink.onRequest(n -> {
                List<String> data = Arrays.asList("hello", "world");
                for(String c : data){
                    fluxSink.next(c);
                }
            });
        }, FluxSink.OverflowStrategy.BUFFER).subscribe();
        eventListeners.stream()
                .forEach(eventListener -> {
                    System.out.println("eventListeners thread:" + Thread.currentThread().getName());
                    List<String> data = Arrays.asList("foo", "bar");
                    eventListener.onDataChunk(data);
                    eventListener.processComplete();
                });
    }
}
