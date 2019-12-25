package com.athena.example.springbootextension.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.TimeUnit;

/**
 * @auther: yusheng
 */
@RestController
public class AsyncController {

    @GetMapping(value = "/get/callable")
    public Callable<String> callable(){
        return () -> {
            TimeUnit.SECONDS.sleep(3);
            return "Hello World";
        };
    }

    @GetMapping(value = "/get/completion")
    public CompletionStage<String> completionStage(){
        System.out.println(Thread.currentThread().getName());
        return CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName());
            return "Hello World 1";
        }).thenApply((str) -> {
            System.out.println(Thread.currentThread().getName());
            return str + " 2";
        });
    }


    public static void main(String[] args) {
        Map<Long,Long> maps = new HashMap<Long, Long>();
        maps.put(1L, 2L);
        System.out.println(maps.get(5L));
    }

    @PostConstruct
    public void postCon(){
        System.out.println("postCon方法执行!");
    }

    @PreDestroy
    public void destroy(){
        System.out.println("destroy方法执行!");
    }
}
