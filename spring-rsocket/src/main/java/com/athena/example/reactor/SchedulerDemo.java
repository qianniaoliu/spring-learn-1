package com.athena.example.reactor;

import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author yusheng
 */
public class SchedulerDemo {

    public static void main(String[] args) throws InterruptedException {
//        Flux<String> flux = Flux.defer(() -> create());
//        System.out.println("开始创建链路");
//        flux.map(item -> {
//            System.out.println("11当前线程:" + Thread.currentThread().getName());
//            return item;
//        })
////                .publishOn(Schedulers.newSingle("pubSingle"))
//                .map(item -> {
//                    if("bar".equals(item)){
//                        throw new RuntimeException("失败");
//                    }
//                    System.out.println("22当前线程:" + Thread.currentThread().getName());
//                    return item;
//                })
//                .doOnError(error -> {
//                    System.out.println("当前异常：" + error.getMessage());
//                })
//
////                .subscribeOn(Schedulers.newSingle("subSingle"))
//                .subscribe();
//        System.out.println(111);

        Flux.interval(Duration.ofSeconds(1))
                .map(item -> {
                    if(item < 3){
                        return "tick" + item;
                    }
                    throw new RuntimeException("boom");
                })
                .retry(1)
                .elapsed()
                .subscribe(System.out::println, System.err::println);
        Thread.sleep(1000000);
    }

    private static Flux<String> create() {
        System.out.println("创建元数据,当前线程:" + Thread.currentThread().getName());
        return Flux.just("foo", "bar", "hello", "world");
    }
}
