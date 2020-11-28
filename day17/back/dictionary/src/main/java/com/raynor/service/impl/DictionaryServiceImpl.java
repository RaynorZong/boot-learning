package com.raynor.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.raynor.entity.Dictionary;
import com.raynor.mapper.DictionaryMapper;
import com.raynor.service.IDictionaryService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Raynor
 * @since 2020-04-18
 */
@Service
public class DictionaryServiceImpl extends ServiceImpl<DictionaryMapper, Dictionary> implements IDictionaryService {

}
