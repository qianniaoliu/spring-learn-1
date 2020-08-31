package com.athena.retry;

import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.support.RetryTemplate;

/**
 * @author yusheng
 */
public class SpringRetryDemo {

    public static void main(String[] args) {
        RetryTemplate template = new RetryTemplate();
        SimpleRetryPolicy policy = new SimpleRetryPolicy();
        policy.setMaxAttempts(3);
        template.setRetryPolicy(policy);
        String result = template.execute(context -> {
            throw new RuntimeException("lalala");
        }, context -> {
            throw new RuntimeException("heiheihei");
        });

        System.out.println(result);
    }
}
