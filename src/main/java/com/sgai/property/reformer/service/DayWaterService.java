package com.sgai.property.reformer.service;

import com.sgai.property.reformer.entity.DayWater;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @author ppliu
 * created in 2019/1/23 15:01
 */
public interface DayWaterService {
    List<DayWater> selectByRecentDay(int recentDays);

    List<DayWater> selectOneDay(int recentDays);

    List<DayWater> selectByExample(Example example);

    DayWater selectOnByExample(Example dayExample);

    int insertSelective(DayWater dayWater);

    int updateByPrimaryKeySelective(DayWater dayWater);

    DayWater getYesterdayData();

    DayWater getTodayData();
}
