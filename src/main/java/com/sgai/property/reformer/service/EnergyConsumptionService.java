package com.sgai.property.reformer.service;

import com.sgai.property.reformer.entity.EnergyConsumption;
import tk.mybatis.mapper.entity.Example;

/**
 * @author ppliu
 * created in 2019/1/21 18:02
 */
public interface EnergyConsumptionService {
    int insert(EnergyConsumption record);

    int insertSelective(EnergyConsumption record);


    EnergyConsumption selectOneByExample(Example example);
}
