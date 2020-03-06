package com.athena.sample.array;

/**
 * @author yusheng
 */
public class SkipListDemo {

    public static void main(String[] args) {
        SkipList skipList = new SkipList();
        skipList.insert(1);
        skipList.insert(2);
        skipList.insert(3);

        System.out.println(skipList.find(3));
        System.out.println(skipList.find(2));
    }
}
