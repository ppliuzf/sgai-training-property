package com.sgai.property.reformer.service;

import com.sgai.property.reformer.entity.DayEnergyConsumption;
import com.szx.core.service.MapperService;

import java.time.LocalDate;
import java.util.List;

/**
 * @author ppliu
 * created in 2019/1/22 10:11
 */
public interface DayEnergyConsumptionService extends MapperService<DayEnergyConsumption> {
    /**
     *获取过去7天的数据.
     */
    List<DayEnergyConsumption> getLast7DaysData(LocalDate localDate);
}
