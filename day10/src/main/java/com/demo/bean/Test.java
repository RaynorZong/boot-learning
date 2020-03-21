package com.demo.bean;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;

@Slf4j
public class Test implements BeanNameAware, BeanFactoryAware, InitializingBean, DisposableBean {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        System.out.println("setName"+name);
        this.name = name;

    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println(beanFactory.toString());
    }

    @Override
    public void setBeanName(String s) {
        System.out.println("beanName:"+s);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("afterPropertiesSet");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("destroy");
    }

    private void myInit() {
        System.out.println("myInit");
    }

    private void myDestroy() {
        System.out.println("myDes");
    }


}
