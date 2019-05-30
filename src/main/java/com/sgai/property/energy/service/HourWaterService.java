package com.sgai.property.energy.service;

import com.sgai.property.energy.entity.HourWater;
import com.szx.core.service.MapperService;

/**
 * 描述:
 *
 * @author ppliu
 * created in 2019/5/23 19:03
 */
public interface HourWaterService extends MapperService<HourWater> {
    void record();
}
