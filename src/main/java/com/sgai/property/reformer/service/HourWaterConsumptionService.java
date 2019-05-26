package com.sgai.property.reformer.service;

import com.sgai.property.reformer.entity.HourWaterConsumption;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @author ppliu
 * created in 2019/1/23 15:03
 */
public interface HourWaterConsumptionService {
    List<HourWaterConsumption> selectOneDay();

    int insertSelective(HourWaterConsumption hourWaterConsumption);

    List<HourWaterConsumption> selectByExample(Example example);
}
