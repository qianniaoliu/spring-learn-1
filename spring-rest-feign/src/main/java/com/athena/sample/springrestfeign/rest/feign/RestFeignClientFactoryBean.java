package com.athena.sample.springrestfeign.rest.feign;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.core.ParameterNameDiscoverer;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.lang.Nullable;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author yusheng
 */
public class RestFeignClientFactoryBean implements FactoryBean, ApplicationContextAware {

    private String serviceName;

    private Class<?> clientClazz;

    private ApplicationContext applicationContext;

    private final ParameterNameDiscoverer  parameterNameDiscoverer = new DefaultParameterNameDiscoverer();


    private final Logger logger = LoggerFactory.getLogger(getClass());


    @Override
    public Object getObject() throws Exception {
        return Proxy.newProxyInstance(clientClazz.getClassLoader(), new Class<?>[]{clientClazz}, new RestFeignClientInvocationHandler());
    }

    @Override
    public Class<?> getObjectType() {
        return clientClazz;
    }

    @Override
    public void setApplicationContext(@Nullable ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    private class RestFeignClientInvocationHandler implements InvocationHandler{

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            RestTemplate restTemplate = applicationContext.getBean(RestTemplate.class);
            GetMapping getMapping = AnnotationUtils.findAnnotation(method, GetMapping.class);

            String[] uris = getMapping.value();

            Annotation[][] parameterAnnotations = method.getParameterAnnotations();
            StringBuilder sb = new StringBuilder(serviceName);
            sb.append(uris[0]).append("?");
            for(Annotation[] annotations : parameterAnnotations){
                RequestParam requestParam = (RequestParam) annotations[0];
                String paramValue = requestParam.value();
                if(!StringUtils.hasText(paramValue)){
                    String[] parameterNames = parameterNameDiscoverer.getParameterNames(method);
                    paramValue = parameterNames[0];
                }
                sb.append("&").append(paramValue).append("=").append(args[0]);
            }
            return restTemplate.getForObject(sb.toString(), method.getReturnType());
        }
    }




    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public Class<?> getClientClazz() {
        return clientClazz;
    }

    public void setClientClazz(Class<?> clientClazz) {
        this.clientClazz = clientClazz;
    }
}
