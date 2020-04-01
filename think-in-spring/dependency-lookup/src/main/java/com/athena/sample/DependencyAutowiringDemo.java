package com.athena.sample;

import com.athena.sample.annotation.Group;
import org.springframework.aop.aspectj.annotation.AnnotationAwareAspectJAutoProxyCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.MutablePropertySources;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * @author yusheng
 */
@EnableAspectJAutoProxy
public class DependencyAutowiringDemo {

    @Autowired
    private Collection<User> users;

    @Autowired
    @Qualifier
    private Collection<User> qualifierUsers;

    @Autowired
    @Group
    private Collection<User> groupUsers;

    @Value("${athena}")
    private String athena;

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(DependencyAutowiringDemo.class);
        ConfigurableEnvironment environment = applicationContext.getEnvironment();
        MutablePropertySources mutablePropertySources = environment.getPropertySources();
        Map<String, Object> values = new HashMap<>();
        values.put("athena", "nacos");
        MapPropertySource mapPropertySource = new MapPropertySource("custom-properties", values);
        mutablePropertySources.addLast(mapPropertySource);
        applicationContext.refresh();
        DependencyAutowiringDemo demo = applicationContext.getBean(DependencyAutowiringDemo.class);

        AnnotationAwareAspectJAutoProxyCreator proxyCreator = applicationContext.getBean(AnnotationAwareAspectJAutoProxyCreator.class);
        System.out.println(demo.users);
        System.out.println(demo.qualifierUsers);
        System.out.println(demo.groupUsers);
        applicationContext.close();
    }

    @Bean
    public User user1(){
        return createUser(1);
    }

    @Bean
    @Qualifier
    public User user2(){
        return createUser(2);
    }

    @Bean
    @Group
    public User user3(){
        return createUser(3);
    }

    @Bean
    public static User user4(){
        return createUser(4);
    }

    private static User createUser(Integer id){
        User user = new User();
        user.setId(id);
        return user;
    }
}
