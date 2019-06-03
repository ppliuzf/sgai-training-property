package com.sgai.property.reformer.service;

import com.sgai.property.reformer.entity.DayWaterConsumption;
import com.szx.core.service.MapperService;

import java.time.LocalDate;
import java.util.List;

/**
 * @author ppliu
 * created in 2019/1/23 15:01
 */
public interface DayWaterConsumptionService extends MapperService<DayWaterConsumption> {
    /**
     *获取过去7天的数据.
     */
    List<DayWaterConsumption> getLast7DaysData(LocalDate localDate);
}
