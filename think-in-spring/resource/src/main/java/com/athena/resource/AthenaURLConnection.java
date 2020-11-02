package com.athena.resource;

import org.springframework.core.io.ClassPathResource;
import sun.net.www.URLConnection;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * @author yusheng
 */
public class AthenaURLConnection extends URLConnection {

    private final ClassPathResource resource;

    /**
     * Create a URLConnection object.  These should not be created directly:
     * instead they should be created by protocol handers in response to
     * URL.openConnection.
     *
     * @param u The URL that this connects to.
     */
    public AthenaURLConnection(URL u) {
        super(u);
        this.resource = new ClassPathResource(u.getPath());
    }

    @Override
    public void connect() throws IOException {

    }

    @Override
    public InputStream getInputStream() throws IOException {
        return resource.getInputStream();
    }
}
