package com.demo.service.impl;

import com.demo.mapper.MessageMapper;
import com.demo.model.Message;
import com.demo.service.MessageService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (TMessage)表服务实现类
 *
 * @author makejava
 * @since 2020-02-19 12:28:15
 */
@Service("messageService")
public class MessageServiceImpl implements MessageService {
    @Resource
    private MessageMapper messageMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param xlh 主键
     * @return 实例对象
     */
    @Override
    public Message queryById(String xlh) {
        return this.messageMapper.queryById(xlh);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<Message> queryAllByLimit(int offset, int limit) {
        return this.messageMapper.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param message 实例对象
     * @return 实例对象
     */
    @Override
    public Message insert(Message message) {
        this.messageMapper.insert(message);
        return message;
    }

    /**
     * 修改数据
     *
     * @param message 实例对象
     * @return 实例对象
     */
    @Override
    public Message update(Message message) {
        this.messageMapper.update(message);
        return this.queryById(message.getXlh());
    }

    /**
     * 通过主键删除数据
     *
     * @param xlh 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String xlh) {
        return this.messageMapper.deleteById(xlh) > 0;
    }
}