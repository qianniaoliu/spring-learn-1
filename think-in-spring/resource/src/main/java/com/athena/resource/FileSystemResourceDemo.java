package com.athena.resource;

import org.apache.commons.io.IOUtils;
import org.springframework.core.io.FileSystemResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import java.io.IOException;
import java.util.stream.Stream;

/**
 * @author yusheng
 */
public class FileSystemResourceDemo {

    public static void main(String[] args) throws IOException {
        FileSystemResourceLoader resourceLoader = new FileSystemResourceLoader();
        PathMatchingResourcePatternResolver patternResolver = new PathMatchingResourcePatternResolver(resourceLoader);
        Resource[] resources = patternResolver.getResources("D:\\work\\cloud\\spring-learn-1\\think-in-spring\\resource\\src\\main\\java\\com\\athena\\resource\\FileSystemResourceDemo.java");
        Stream.of(resources)
                .forEach(resource -> {
                    try {
                        EncodedResource encodedResource = new EncodedResource(resource, "UTF-8");
                        String content = IOUtils.toString(encodedResource.getReader());
                        System.out.println(content);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
    }
}
