package com.demo;

import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class TestController {

    @GetMapping("/test")
    public String test() throws InterruptedException {
        initFlowRules();
        for (int i = 0; i < 1000; i++) {
            Entry entry = null;
            try {
                entry = SphU.entry("HelloWorld");
                System.out.println("hello world");

            }catch (BlockException e){
                System.out.println("block");

            }finally {
                if(entry!=null){
                    entry.exit();
                }
            }

        }
        return  "";
    }
    @GetMapping("/test1")
    public String test1(HttpServletRequest request,HttpServletResponse response) throws InterruptedException {
        try {

            response.setHeader("content-type", "text/json;charset=UTF-8");
            response.getWriter().write("哈哈哈");
            response.getWriter().flush();
         } catch (IOException e) {
            e.printStackTrace();
        }
        return  "";
    }
    private static void initFlowRules() {
        List<FlowRule> rules = new ArrayList<>();
        FlowRule rule = new FlowRule();
        rule.setResource("HelloWorld");
        rule.setGrade(RuleConstant.FLOW_GRADE_QPS);
        // Set limit QPS to 20.
        rule.setCount(20);
        rules.add(rule);
        FlowRuleManager.loadRules(rules);
    }
}
