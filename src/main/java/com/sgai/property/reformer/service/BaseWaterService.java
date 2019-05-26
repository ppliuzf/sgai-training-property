package com.sgai.property.reformer.service;

import com.sgai.property.reformer.entity.BaseWater;
import tk.mybatis.mapper.entity.Example;

/**
 * @author ppliu
 * created in 2019/1/23 15:05
 */
public interface BaseWaterService {
    BaseWater selectOneByExample(Example example);

    int insertSelective(BaseWater baseWater);
}
