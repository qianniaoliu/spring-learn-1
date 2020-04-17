package com.athena.sample.asm;

import org.springframework.asm.ClassVisitor;

/**
 * @author yusheng
 */
public class ClassAdapter extends ClassVisitor {
    public ClassAdapter(int api) {
        super(api);
    }
}
