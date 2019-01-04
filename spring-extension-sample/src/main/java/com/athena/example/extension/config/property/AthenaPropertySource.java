package com.athena.example.extension.config.property;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @auther: yusheng
 */
@PropertySource(name = "athenaPropertySource", value = {"classpath:athena.properties"})
@Configuration
public class AthenaPropertySource {
}
