package com.demo.config;

import com.alibaba.fastjson.JSON;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.support.converter.AbstractMessageConverter;
import org.springframework.amqp.support.converter.MessageConversionException;

public class SelfConverter extends AbstractMessageConverter {
    @Override
    protected Message createMessage(Object o, MessageProperties messageProperties) {
        messageProperties.setContentType("application/json");
        return new Message(JSON.toJSONBytes(o), messageProperties);
    }

    @Override
    public Object fromMessage(Message message) throws MessageConversionException {
        return JSON.parse(message.getBody());
    }
}
