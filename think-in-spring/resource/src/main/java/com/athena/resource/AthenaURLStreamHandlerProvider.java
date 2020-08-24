package com.athena.resource;

import java.net.URLStreamHandler;
import java.net.spi.URLStreamHandlerProvider;

/**
 * @author yusheng
 */
public class AthenaURLStreamHandlerProvider extends URLStreamHandlerProvider {
    @Override
    public URLStreamHandler createURLStreamHandler(String protocol) {
        return new Handler();
    }
}
