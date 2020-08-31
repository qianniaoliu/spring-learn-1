package com.athena.sample.jsf;

import java.util.List;

/**
 * @author yusheng
 */
public class User {

    private byte[] bytes;

    private List<byte[]> allBytes;

    public byte[] getBytes() {
        return bytes;
    }

    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }

    public List<byte[]> getAllBytes() {
        return allBytes;
    }

    public void setAllBytes(List<byte[]> allBytes) {
        this.allBytes = allBytes;
    }
}
