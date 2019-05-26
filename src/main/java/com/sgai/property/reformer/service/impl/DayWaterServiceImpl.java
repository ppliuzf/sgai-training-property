package com.sgai.property.reformer.service.impl;

import com.sgai.property.reformer.entity.DayWater;
import com.sgai.property.reformer.mapper.DayWaterMapper;
import com.sgai.property.reformer.service.DayWaterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @author ppliu
 * created in 2019/1/23 15:01
 */
@Service
public class DayWaterServiceImpl implements DayWaterService {
    @Autowired
    private DayWaterMapper dayWaterMapper;

    @Override
    public List<DayWater> selectByRecentDay(int recentDays) {
        Example example = new Example(DayWater.class);
        example.createCriteria().andCondition("DATE_SUB(CURDATE(), INTERVAL " + recentDays + " DAY) < date(record_time)");
        example.orderBy("recordTime").asc();
        return selectByExample(example);
    }

    @Override
    public List<DayWater> selectOneDay(int recentDays) {
        Example example = new Example(DayWater.class);
        example.createCriteria().andCondition("DATE_SUB(CURDATE(), INTERVAL " + recentDays + " DAY) = date(record_time)");
        example.orderBy("recordTime").asc();
        return selectByExample(example);
    }


    @Override
    public List<DayWater> selectByExample(Example example) {
        return dayWaterMapper.selectByExample(example);
    }

    @Override
    public DayWater selectOnByExample(Example dayExample) {
        return dayWaterMapper.selectOneByExample(dayExample);
    }

    @Override
    public int insertSelective(DayWater dayWater) {
        return dayWaterMapper.insertSelective(dayWater);
    }

    @Override
    public int updateByPrimaryKeySelective(DayWater dayWater) {
        return dayWaterMapper.updateByPrimaryKeySelective(dayWater);
    }

    @Override
    public DayWater getYesterdayData() {
        List<DayWater> dayWaterList = selectOneDay(1);
        return dayWaterList.isEmpty() ? DayWater.emptyRecord() : dayWaterList.get(0);
    }

    @Override
    public DayWater getTodayData() {
        List<DayWater> dayWaterList = selectOneDay(0);
        return dayWaterList.isEmpty() ? DayWater.emptyRecord() : dayWaterList.get(0);
    }
}
