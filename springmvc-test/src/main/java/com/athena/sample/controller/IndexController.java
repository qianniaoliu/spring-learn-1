package com.athena.sample.controller;

import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import com.athena.sample.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.AsyncContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

/**
 * @author yusheng
 */
@RestController
public class IndexController {

    private final Logger logger = LoggerFactory.getLogger(IndexController.class);

    @Autowired
    private User user;

    @Autowired
    private User user1;

    @GetMapping(value = "/index")
    public String index() {
        initFlowRules();
        Entry entry = null;
        try {
            entry = SphU.entry("HelloWorld");
        } catch (BlockException ex) {
            logger.info("被限流了");
            return "被限流了";
        } finally {
            if (entry != null) {
                entry.exit();
            }
        }
        return "index";
    }

    @GetMapping(value = "/test/{test}")
    public String test(@PathVariable String test) {
        Method method = null;
        try {
            method = IndexController.class.getMethod("test", String.class);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        RequestMapping requestMapping = AnnotationUtils.getAnnotation(method, RequestMapping.class);
        return requestMapping.value()[0];
    }

    @PostMapping("/listener")
    public void listener(HttpServletRequest request, HttpServletResponse response) {
        AsyncContext asyncContext = request.startAsync();
        CompletableFuture.runAsync(() -> {
            try {
                Thread.sleep(5000);
                HttpServletResponse resp = (HttpServletResponse) asyncContext.getResponse();
                resp.setStatus(HttpServletResponse.SC_OK);
                resp.getWriter().println("asyncContext complete");
                asyncContext.complete();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }


    private static void initFlowRules() {
        List<FlowRule> rules = new ArrayList<>();
        FlowRule rule = new FlowRule();
        rule.setResource("HelloWorld");
        rule.setGrade(RuleConstant.FLOW_GRADE_QPS);
        // Set limit QPS to 20.
        rule.setCount(2);
        rules.add(rule);
        FlowRuleManager.loadRules(rules);
    }

    public static void main(String[] args) {
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
        executorService.execute(() -> {
            try {
                Thread.sleep(1000);
                System.out.println(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        });
        System.out.println(22);
    }

    @GetMapping("/rest/index")
    public String hello(@RequestParam String message) {
        return "监听到远程调用消息啦,message:" + message;
    }

    @PostMapping(value = "/rest/file", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String file(@RequestParam(value = "message") MultipartFile message) {
        return "监听到远程调用消息啦,message:";
    }

    @RequestMapping("/subsStatus.html")
    public String testForm(String imsi, String IMSIshow, HttpServletRequest request){
        return "SUCCESS";
    }

}
