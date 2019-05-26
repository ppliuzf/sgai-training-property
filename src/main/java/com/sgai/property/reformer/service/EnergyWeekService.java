package com.sgai.property.reformer.service;

import com.sgai.property.reformer.vo.EnergyWeekSummary;

/**
 * @author ppliu
 * created in 2019/3/25 12:07
 */
public interface EnergyWeekService {
    /**
     * 通过位置查询周能源汇总.
     */
     EnergyWeekSummary getWeekSummaryByPosition(String position);
}
