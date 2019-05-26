package com.sgai.property.reformer.service.impl;

import com.sgai.property.reformer.entity.DayEnergyConsumption;
import com.sgai.property.reformer.entity.HourEnergyConsumption;
import com.sgai.property.reformer.mapper.DayEnergyConsumptionMapper;
import com.sgai.property.reformer.service.DayEnergyConsumptionService;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @author ppliu
 * created in 2019/1/22 10:11
 */
@Service
public class DayEnergyConsumptionServiceImpl implements DayEnergyConsumptionService {
    @Autowired
    private DayEnergyConsumptionMapper dayEnergyConsumptionMapper;

    @Override
    public DayEnergyConsumption selectOnByExample(Example dayExample) {
        return dayEnergyConsumptionMapper.selectOneByExample(dayExample);
    }

    @Override
    public int insertSelective(DayEnergyConsumption dayEnergyConsumption) {
        return dayEnergyConsumptionMapper.insertSelective(dayEnergyConsumption);
    }

    @Override
    public int updateByPrimaryKeySelective(DayEnergyConsumption dayEnergyConsumption) {
        return dayEnergyConsumptionMapper.updateByPrimaryKeySelective(dayEnergyConsumption);
    }

    @Override
    public List<DayEnergyConsumption> selectByRecentDay(int days) {
        Example example = new Example(HourEnergyConsumption.class);
        example.createCriteria().andCondition("DATE_SUB(CURDATE(), INTERVAL " + days + " DAY) < date(record_time)");
        example.orderBy("recordTime").asc();
        List<DayEnergyConsumption> dayEnergyConsumptionList = selectByExample(example);
        dayEnergyConsumptionList.forEach(dayEnergyConsumption -> {
            dayEnergyConsumption.setStrRecordTime(DateFormatUtils.format(dayEnergyConsumption.getRecordTime(), "yyyy/MM/dd"));
        });
        return dayEnergyConsumptionList;
    }

    @Override
    public List<DayEnergyConsumption> selectOneDay(int days) {
        Example example = new Example(HourEnergyConsumption.class);
        example.createCriteria().andCondition("DATE_SUB(CURDATE(), INTERVAL " + days + " DAY) = date(record_time)");
        example.orderBy("recordTime").asc();
        List<DayEnergyConsumption> dayEnergyConsumptionList = selectByExample(example);
        dayEnergyConsumptionList.forEach(dayEnergyConsumption -> {
            dayEnergyConsumption.setStrRecordTime(DateFormatUtils.format(dayEnergyConsumption.getRecordTime(), "yyyy/MM/dd"));
        });
        return dayEnergyConsumptionList;
    }

    @Override
    public List<DayEnergyConsumption> selectByExample(Example example) {
        return dayEnergyConsumptionMapper.selectByExample(example);
    }

    @Override
    public DayEnergyConsumption getTodayData() {
        List<DayEnergyConsumption> dayEnergyConsumptionList = selectOneDay(0);
        return dayEnergyConsumptionList.isEmpty() ? DayEnergyConsumption.emptyRecord() : dayEnergyConsumptionList.get(0);
    }

    @Override
    public DayEnergyConsumption getYesterdayData() {
        List<DayEnergyConsumption> dayEnergyConsumptionList = selectOneDay(1);
        return dayEnergyConsumptionList.isEmpty() ? DayEnergyConsumption.emptyRecord() : dayEnergyConsumptionList.get(0);
    }

    @Override
    public List<DayEnergyConsumption> getLastWeekDataList() {
        Example example = new Example(DayEnergyConsumption.class);
        example.createCriteria().andCondition("YEARWEEK( date_format(  record_time,'%Y-%m-%d' ) ) = YEARWEEK( now() ) ");
        return selectByExample(example);
    }

    @Override
    public List<DayEnergyConsumption> getThisWeekDataList() {
        Example example = new Example(DayEnergyConsumption.class);
        example.createCriteria().andCondition("YEARWEEK( date_format(  record_time,'%Y-%m-%d' ) ) = YEARWEEK( now() )-1 ");
        return selectByExample(example);
    }
}
