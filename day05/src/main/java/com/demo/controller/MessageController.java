package com.demo.controller;

import com.demo.model.Message;
import com.demo.service.MessageService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * (TMessage)表控制层
 *
 * @author makejava
 * @since 2020-02-19 12:28:15
 */
@RestController
@RequestMapping("/message")
public class MessageController {
    /**
     * 服务对象
     */
    @Resource
    private MessageService messageService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/selectOne")
    public Message selectOne(String id) {
        return this.messageService.queryById(id);
    }

}