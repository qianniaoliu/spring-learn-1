package com.athena.sample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.lang.reflect.Method;
import java.util.List;

/**
 * @author yusheng
 */
public class Demo {

    @Autowired
    private List<EchoService> echoServiceList;

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new  AnnotationConfigApplicationContext();
        applicationContext.register(HelloEchoService.class, WorldEchoService.class, Demo.class);
        applicationContext.refresh();

        Demo demo = applicationContext.getBean(Demo.class);
        System.out.println(demo.echoServiceList.size());

        ExecutorComposite<EchoService> executorComposite = new ExecutorComposite<>(EchoService.class, demo.echoServiceList);
        executorComposite.execute("noRes",new Class[]{String.class}, new Object[]{"Hello,World"} );
        applicationContext.close();
    }

    private static class ExecutorComposite<S>{

        private List<S> objects;

        private Class<S> clazz;

        public ExecutorComposite(Class<S> clazz, List<S> objects){
            this.clazz = clazz;
            this.objects = objects;
        }

        public void execute(String methodName, Class[] paramTypes, Object[] args){
            try {
                Method method = clazz.getMethod(methodName, paramTypes);
                for(S source : objects){
                    method.invoke(source, args);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
