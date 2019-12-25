package com.athena.example.extension;

import com.athena.example.extension.config.spring.context.annotation.AthenaComponentScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @auther: yusheng
 */
@SpringBootApplication
@AthenaComponentScan(basePackages = {"com.athena.example.extension.app.custom"})
public class SpringExtensionSampleApplication {

    public static void main(String[] args) {
        /*ConfigurableApplicationContext athenaContext = new AnnotationConfigApplicationContext();
        athenaContext.setId("余生");
        ((AnnotationConfigApplicationContext) athenaContext).registerBean("helloworld", String.class);
        athenaContext.refresh();*/
        ConfigurableApplicationContext context = new SpringApplicationBuilder(SpringExtensionSampleApplication.class)
//                .parent(athenaContext)
                .run(args);
    }
}
