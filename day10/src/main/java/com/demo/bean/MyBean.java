package com.demo.bean;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;

@Component
public class MyBean implements FactoryBean<Object> {


    @Override
    public Object getObject() throws Exception {
        return null;
    }

    @Override
    public Class<?> getObjectType() {
        return null;
    }
}
