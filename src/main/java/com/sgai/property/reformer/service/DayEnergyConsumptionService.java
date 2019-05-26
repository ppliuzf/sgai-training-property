package com.sgai.property.reformer.service;

import com.sgai.property.reformer.entity.DayEnergyConsumption;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @author ppliu
 * created in 2019/1/22 10:11
 */
public interface DayEnergyConsumptionService {
    DayEnergyConsumption selectOnByExample(Example dayExample);

    int insertSelective(DayEnergyConsumption dayEnergyConsumption);

    int updateByPrimaryKeySelective(DayEnergyConsumption dayEnergyConsumption);

    List<DayEnergyConsumption> selectByRecentDay(int days);

    List<DayEnergyConsumption> selectOneDay(int days);

    List<DayEnergyConsumption> selectByExample(Example example);

    DayEnergyConsumption getTodayData();

    DayEnergyConsumption getYesterdayData();

    /**
     * 获取上周的数据.
     */
    List<DayEnergyConsumption> getLastWeekDataList();

    /**
     * 获取本周的数据.
     */
    List<DayEnergyConsumption> getThisWeekDataList();
}
