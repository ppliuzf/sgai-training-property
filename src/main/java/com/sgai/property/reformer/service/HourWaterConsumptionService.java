package com.sgai.property.reformer.service;

import com.sgai.property.reformer.entity.HourWaterConsumption;
import com.szx.core.service.MapperService;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author ppliu
 * created in 2019/1/23 15:03
 */
public interface HourWaterConsumptionService extends MapperService<HourWaterConsumption> {
    /**
     *获取过去7小时的数据.
     */
    List<HourWaterConsumption> getLast7HoursData(LocalDateTime localDateTime);
}
