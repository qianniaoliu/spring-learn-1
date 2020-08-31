package com.athena.sample.jsf;

import com.jd.fastjson.JSONObject;
import com.jd.jsf.gd.GenericService;
import com.jd.jsf.gd.config.ConsumerConfig;
import com.jd.jsf.gd.config.RegistryConfig;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author yusheng
 */
public class JsfApiDemo {

    public static void main(String[] args) {
        // 注册中心实现（必须）
        RegistryConfig jsfRegistry = new RegistryConfig();
        jsfRegistry.setIndex("i.jsf.jd.com"); // 测试环境192.168.150.121 i.jsf.jd.com
        // 服务消费者连接注册中心，设置属性
        ConsumerConfig<GenericService> consumerConfig = new ConsumerConfig<GenericService>();
        consumerConfig.setInterfaceId("com.athena.sample.jsf.EchoService");// 这里写真实的类名
        consumerConfig.setRegistry(jsfRegistry);
        consumerConfig.setProtocol("jsf");
        consumerConfig.setAlias("sl.1.1");
        consumerConfig.setGeneric(true); // 需要指定是Generic调用true
        // consumerConfig.setAsync(true); // 如果异步
        // 得到泛化调用实例，此操作很重，请缓存consumerConfig或者service对象！！！！（用map或者全局变量）
        GenericService service = consumerConfig.refer();
        Map<String, Object> params = new HashMap<>();
        List<String> allBytes = new ArrayList<>();
        allBytes.add("Athena".getBytes().toString());
        allBytes.add("Jessica".getBytes().toString());
        params.put("bytes", "Hello,World".getBytes().toString());
        params.put("allBytes", allBytes);
        // 传入方法名，参数类型，参数值
        Object result = service.$invoke("hello", new String[]{"com.athena.sample.jsf.User"},
                new Object[]{params});
        System.out.println(result);
    }
}
