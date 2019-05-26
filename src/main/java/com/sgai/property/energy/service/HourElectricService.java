package com.sgai.property.energy.service;

import com.sgai.property.energy.entity.HourElectric;
import com.sgai.property.reformer.entity.HourEnergyConsumption;
import com.szx.core.service.MapperService;

/**
 * 描述:
 *
 * @author ppliu
 * created in 2019/5/23 19:01
 */
public interface HourElectricService extends MapperService<HourElectric> {
    HourEnergyConsumption getRecentDataByMeterCode();
}
