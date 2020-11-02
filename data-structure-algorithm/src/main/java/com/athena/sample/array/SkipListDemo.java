package com.athena.sample.array;

import java.util.HashMap;
import java.util.Map;

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
        Map<String, String> maps = new HashMap<>();
        maps.put("lala", null);
        maps.put("lala1", null);
        maps.put("lala2", null);
        System.out.println(maps.keySet());
    }
}
