package com.sgai.property.reformer.service;

import com.sgai.property.reformer.entity.HourEnergyConsumption;
import com.szx.core.service.MapperService;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author ppliu
 * created in 2019/1/22 10:07
 */
public interface HourEnergyConsumptionService extends MapperService<HourEnergyConsumption> {
    /**
     *获取过去7小时的数据.
     */
     List<HourEnergyConsumption> getLast7HoursData(LocalDateTime localDateTime);
}
