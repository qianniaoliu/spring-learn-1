package com.athena.sample.array;

import java.util.PriorityQueue;

/**
 * @author yusheng
 */
public class PriorityQueueDemo {

    public static void main(String[] args) {
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(8);
        priorityQueue.add(123);
        priorityQueue.add(11);
        priorityQueue.add(20);
        priorityQueue.add(12321);
        System.out.println(priorityQueue.poll());

        String pic = "As the picture";
        System.out.println("As the picture".equals(pic));
    }
}
