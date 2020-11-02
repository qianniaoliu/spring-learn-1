package com.athena.sample.jsf;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yusheng
 */
@Service
public class EchoServiceImpl implements EchoService {
    @Override
    public String hello(User user) {
        List<String> result = new ArrayList<>();
        result.add(new String(user.getBytes()));
        for(byte[] bytes : user.getAllBytes()){
            result.add(new String(bytes));
        }
        return result.toString();
    }
}
