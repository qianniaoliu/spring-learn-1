package com.athena.sample.asm;

import org.springframework.asm.*;
import org.springframework.core.type.classreading.AnnotationMetadataReadingVisitor;

/**
 * @author yusheng
 */
public class ClassReaderDemo {

    public static void main(String[] args) throws Exception{
        ClassReader classReader = new ClassReader("com.athena.sample.model.User");
//        classReader.accept(new AthenaClassVisitor(Opcodes.ASM7), ClassReader.SKIP_DEBUG);

        AnnotationMetadataReadingVisitor visitor = new AnnotationMetadataReadingVisitor(Thread.currentThread().getContextClassLoader());
        classReader.accept(visitor, ClassReader.SKIP_DEBUG);
        System.out.println(111);
        String[] arr = new String[]{"A", "B"};
        Object xx = arr;
        System.out.println(xx);
        System.out.println(Math.pow(3, 3));

    }

    static class AthenaClassVisitor extends ClassVisitor{
        public AthenaClassVisitor(int api) {
            super(api);
        }

        @Override
        public AnnotationVisitor visitAnnotation(String descriptor, boolean visible) {
            String className = Type.getType(descriptor).getClassName();
            return new AthenaAnnotationVisitor(className);
        }

        @Override
        public FieldVisitor visitField(int access, String name, String descriptor, String signature, Object value) {
            return super.visitField(access, name, descriptor, signature, value);
        }
    }

    static class AthenaAnnotationVisitor extends AnnotationVisitor{

        private String annotationType;


        public AthenaAnnotationVisitor(String annotationType) {
            super(Opcodes.ASM7);
            this.annotationType = annotationType;
        }

        @Override
        public AnnotationVisitor visitAnnotation(String name, String descriptor) {
            String className = Type.getType(descriptor).getClassName();
            return new AthenaAnnotationVisitor(className);
        }
    }

}
