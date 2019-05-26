package com.sgai.property.reformer.service.impl;

import com.sgai.property.reformer.entity.HourWaterConsumption;
import com.sgai.property.reformer.mapper.HourWaterConsumptionMapper;
import com.sgai.property.reformer.service.HourWaterConsumptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author ppliu
 * created in 2019/1/23 15:03
 */
@Service
public class HourWaterConsumptionServiceImpl implements HourWaterConsumptionService {
    @Autowired
    private HourWaterConsumptionMapper hourWaterConsumptionMapper;

    @Override
    public List<HourWaterConsumption> selectOneDay() {
        Example example = new Example(HourWaterConsumption.class);
        example.createCriteria().andCondition("DATEDIFF(record_time,NOW()) in (0,-1)");
        example.orderBy("recordTime").asc();
        List<HourWaterConsumption> total = selectByExample(example);
        Calendar calendar = Calendar.getInstance();
        int today = calendar.get(Calendar.DAY_OF_MONTH);
        //昨天最后两小时的数据.
        List<HourWaterConsumption> yesterdayData = total.stream().filter(hourWater -> {
            calendar.setTime(hourWater.getRecordTime());
            return calendar.get(Calendar.DAY_OF_MONTH) != today;
        }).sorted(Comparator.comparing(HourWaterConsumption::getRecordTime).reversed())
                .limit(2)
                .collect(Collectors.toList());
        List<HourWaterConsumption> todayData = total.stream().filter(hourWater -> {
            calendar.setTime(hourWater.getRecordTime());
            return calendar.get(Calendar.DAY_OF_MONTH) == today;
        }).sorted(Comparator.comparing(HourWaterConsumption::getRecordTime).reversed())
                .collect(Collectors.toList());
        Map<Integer, List<HourWaterConsumption>> map = todayData.stream().filter(hourWater -> {
            calendar.setTime(hourWater.getRecordTime());
            return calendar.get(Calendar.HOUR_OF_DAY) <= 21;
        }).collect(Collectors.groupingBy(hourWater -> {
            calendar.setTime(hourWater.getRecordTime());
            int hour = calendar.get(Calendar.HOUR_OF_DAY);
            if (hour == 0) {
                return 0;
            } else if (hour <= 3) {
                return 3;
            } else if (hour <= 6) {
                return 6;
            } else if (hour <= 9) {
                return 9;
            } else if (hour <= 12) {
                return 12;
            } else if (hour <= 15) {
                return 15;
            } else if (hour <= 18) {
                return 18;
            } else {
                return 21;
            }
        }));
        List<HourWaterConsumption> result = new ArrayList<>();
        map.forEach((k, v) -> {
            Calendar c = Calendar.getInstance();
            c.set(Calendar.HOUR_OF_DAY,k);
            c.set(Calendar.MINUTE,0);
            c.set(Calendar.SECOND,0);
            c.set(Calendar.MILLISECOND,0);
            HourWaterConsumption hourWaterConsumption = new HourWaterConsumption();
            hourWaterConsumption.setStrRecordTime(k + ":00");
            hourWaterConsumption.setRecordTime(c.getTime());
            hourWaterConsumption.setSlip(v.stream().map(HourWaterConsumption::getSlip).reduce(new BigDecimal(0), BigDecimal::add));
            hourWaterConsumption.setPuck(v.stream().map(HourWaterConsumption::getPuck).reduce(new BigDecimal(0), BigDecimal::add));
            hourWaterConsumption.setCurling(v.stream().map(HourWaterConsumption::getCurling).reduce(new BigDecimal(0), BigDecimal::add));
            hourWaterConsumption.setTotal(v.stream().map(HourWaterConsumption::getTotal).reduce(new BigDecimal(0), BigDecimal::add));
            if (k == 0) {
                hourWaterConsumption.setPuck(yesterdayData.stream().map(HourWaterConsumption::getPuck).reduce(new BigDecimal(0), BigDecimal::add).add(v.stream().map(HourWaterConsumption::getPuck).reduce(new BigDecimal(0), BigDecimal::add)));
                hourWaterConsumption.setSlip(yesterdayData.stream().map(HourWaterConsumption::getSlip).reduce(new BigDecimal(0), BigDecimal::add).add(v.stream().map(HourWaterConsumption::getSlip).reduce(new BigDecimal(0), BigDecimal::add)));
                hourWaterConsumption.setCurling(yesterdayData.stream().map(HourWaterConsumption::getCurling).reduce(new BigDecimal(0), BigDecimal::add).add(v.stream().map(HourWaterConsumption::getCurling).reduce(new BigDecimal(0), BigDecimal::add)));
                hourWaterConsumption.setTotal(yesterdayData.stream().map(HourWaterConsumption::getTotal).reduce(new BigDecimal(0), BigDecimal::add).add(v.stream().map(HourWaterConsumption::getTotal).reduce(new BigDecimal(0), BigDecimal::add)));
            }
            result.add(hourWaterConsumption);
        });
        return result;
    }

    @Override
    public int insertSelective(HourWaterConsumption hourWaterConsumption) {
        return hourWaterConsumptionMapper.insertSelective(hourWaterConsumption);
    }

    @Override
    public List<HourWaterConsumption> selectByExample(Example example) {
        return hourWaterConsumptionMapper.selectByExample(example);
    }
}
