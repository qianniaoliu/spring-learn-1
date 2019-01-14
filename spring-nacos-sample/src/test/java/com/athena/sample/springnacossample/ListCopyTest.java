package com.athena.sample.springnacossample;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: yusheng
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class ListCopyTest {



    @Test
    public void testListCopy(){
        List<Integer> integers = new ArrayList<>();
        integers.add(1);
        integers.add(2);
        List<Integer> copyIntegers = integers;

        integers.add(3);
        System.out.println(copyIntegers.size());
    }
}
