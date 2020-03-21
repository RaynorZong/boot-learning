package com.demo.bean;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 * 实例化完成之后调用该接口
 */
public class MyBeanPostProcessor implements BeanPostProcessor {
    //实例化完成，setBeanName/setBeanFactory完成之后调用该方法
    public Object postProcessBeforeInitialization(Object o, String s) throws BeansException {
        System.out.println("postProcessBeforeInitialization");
        return o;
    }
    //全部是实例化完成以后调用该方法
    public Object postProcessAfterInitialization(Object o, String s) throws BeansException {
        System.out.println("postProcessAfterInitialization");
        return o;
    }

}