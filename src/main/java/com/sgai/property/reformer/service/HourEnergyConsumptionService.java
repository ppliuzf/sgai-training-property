package com.sgai.property.reformer.service;

import com.sgai.property.reformer.entity.HourEnergyConsumption;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @author ppliu
 * created in 2019/1/22 10:07
 */
public interface HourEnergyConsumptionService {
    HourEnergyConsumption selectOneByExample(Example example);

    int insertSelective(HourEnergyConsumption hourEnergyConsumption);

    List<HourEnergyConsumption> selectOneDay();

    List<HourEnergyConsumption> selectByExample(Example example);
}
