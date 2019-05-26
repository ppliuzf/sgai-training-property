package com.sgai.property.reformer.service.impl;

import com.sgai.property.reformer.entity.BaseWater;
import com.sgai.property.reformer.mapper.BaseWaterMapper;
import com.sgai.property.reformer.service.BaseWaterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

/**
 * @author ppliu
 * created in 2019/1/23 15:05
 */
@Service
public class BaseWaterServiceImpl implements BaseWaterService {
    @Autowired
    private BaseWaterMapper baseWaterMapper;

    @Override
    public BaseWater selectOneByExample(Example example) {
        return baseWaterMapper.selectOneByExample(example);
    }

    @Override
    public int insertSelective(BaseWater baseWater) {
        return baseWaterMapper.insertSelective(baseWater);
    }
}
