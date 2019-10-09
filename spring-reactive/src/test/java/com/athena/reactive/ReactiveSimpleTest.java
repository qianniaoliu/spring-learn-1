package com.athena.reactive;

import io.reactivex.rxjava3.core.Observable;
import org.junit.Test;

public class ReactiveSimpleTest {

    @Test
    public void testObservable(){
        Observable.fromArray(1,2,3)
                .map(item-> item*item)
                .forEach(System.out::println);
    }
}
