package com.sgai.property.reformer.service.impl;

import com.sgai.property.reformer.entity.EnergyConsumption;
import com.sgai.property.reformer.mapper.EnergyConsumptionMapper;
import com.sgai.property.reformer.service.EnergyConsumptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

/**
 * @author ppliu
 * created in 2019/1/21 18:05
 */
@Service
public class EnergyConsumptionServiceImpl implements EnergyConsumptionService {
    @Autowired
    private EnergyConsumptionMapper energyConsumptionMapper;

    @Override
    public int insert(EnergyConsumption record) {
        return energyConsumptionMapper.insert(record);
    }

    @Override
    public int insertSelective(EnergyConsumption record) {
        return energyConsumptionMapper.insertSelective(record);
    }

    @Override
    public EnergyConsumption selectOneByExample(Example example) {
        return energyConsumptionMapper.selectOneByExample(example);
    }
}
