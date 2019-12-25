package com.athena.sample.springnacossample.http.converter;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.util.IOUtils;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.util.StringUtils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.Collection;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author yusheng
 */
public class PropertiesHttpMessageConverter extends AbstractHttpMessageConverter<Properties> {

    public PropertiesHttpMessageConverter() {
        super(Charset.forName("UTF-8"),new MediaType("application","properties"));
    }

    @Override
    protected boolean supports(Class<?> clazz) {
        return true;
    }

    @Override
    protected Properties readInternal(Class<? extends Properties> clazz, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
        Properties properties = new Properties();
        InputStream in = inputMessage.getBody();
        properties.load(in);
        properties.entrySet().stream()
                .forEach(entry -> {
                    String value = (String) entry.getValue();
                    if(value.matches("^\\[.+\\]$")){
                        List<Object> objectList = JSONArray.parseArray(value, Object.class);
                        properties.put(entry.getKey(), objectList);
                    }
                });
        IOUtils.close(in);
        return properties;
    }

    @Override
    protected void writeInternal(Properties properties, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {
        outputMessage.getBody().close();
    }
}
