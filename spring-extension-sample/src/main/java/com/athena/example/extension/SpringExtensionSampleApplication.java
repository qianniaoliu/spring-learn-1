package com.athena.example.extension;

import com.athena.example.extension.config.spring.annotation.AthenaComponentScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * @auther: yusheng
 */
@SpringBootApplication
@AthenaComponentScan(basePackages = {"com.athena.example.extension.app.custom"})
public class SpringExtensionSampleApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(SpringExtensionSampleApplication.class)
                .run(args);
    }
}
