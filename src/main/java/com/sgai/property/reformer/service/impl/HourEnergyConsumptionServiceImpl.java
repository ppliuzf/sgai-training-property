package com.sgai.property.reformer.service.impl;

import com.sgai.property.reformer.entity.HourEnergyConsumption;
import com.sgai.property.reformer.mapper.HourEnergyConsumptionMapper;
import com.sgai.property.reformer.service.HourEnergyConsumptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author ppliu
 * created in 2019/1/22 10:07
 */
@Service
public class HourEnergyConsumptionServiceImpl implements HourEnergyConsumptionService {
    @Autowired
    private HourEnergyConsumptionMapper hourEnergyConsumptionMapper;

    @Override
    public HourEnergyConsumption selectOneByExample(Example example) {
        return hourEnergyConsumptionMapper.selectOneByExample(example);
    }

    @Override
    public int insertSelective(HourEnergyConsumption hourEnergyConsumption) {
        return hourEnergyConsumptionMapper.insertSelective(hourEnergyConsumption);
    }

    @Override
    public List<HourEnergyConsumption> selectOneDay() {
        Example example = new Example(HourEnergyConsumption.class);
        example.createCriteria().andCondition("DATEDIFF(record_time,NOW()) in (0,-1)");
        example.orderBy("recordTime").asc();
        List<HourEnergyConsumption> total = selectByExample(example);
        Calendar calendar = Calendar.getInstance();
        int today = calendar.get(Calendar.DAY_OF_MONTH);
        //昨天最后两小时的数据.
        List<HourEnergyConsumption> yesterdayData = total.stream().filter(hourEnergyConsumption -> {
            calendar.setTime(hourEnergyConsumption.getRecordTime());
            return calendar.get(Calendar.DAY_OF_MONTH) != today;
        }).sorted(Comparator.comparing(HourEnergyConsumption::getRecordTime).reversed())
                .limit(2)
                .collect(Collectors.toList());
        List<HourEnergyConsumption> todayData = total.stream().filter(hourEnergyConsumption -> {
            calendar.setTime(hourEnergyConsumption.getRecordTime());
            return calendar.get(Calendar.DAY_OF_MONTH) == today;
        }).sorted(Comparator.comparing(HourEnergyConsumption::getRecordTime).reversed())
                .collect(Collectors.toList());
        Map<Integer, List<HourEnergyConsumption>> map = todayData.stream().filter(hourEnergyConsumption -> {
            calendar.setTime(hourEnergyConsumption.getRecordTime());
            return calendar.get(Calendar.HOUR_OF_DAY) <= 21;
        }).collect(Collectors.groupingBy(hourEnergyConsumption -> {
            calendar.setTime(hourEnergyConsumption.getRecordTime());
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
        List<HourEnergyConsumption> result = new ArrayList<>();
        map.forEach((k, v) -> {
            Calendar c = Calendar.getInstance();
            c.set(Calendar.HOUR_OF_DAY,k);
            c.set(Calendar.MINUTE,0);
            c.set(Calendar.SECOND,0);
            c.set(Calendar.MILLISECOND,0);
            HourEnergyConsumption hourEnergyConsumption = new HourEnergyConsumption();
            hourEnergyConsumption.setStrRecordTime(k + ":00");
            hourEnergyConsumption.setRecordTime(c.getTime());
            hourEnergyConsumption.setSlip(v.stream().map(HourEnergyConsumption::getSlip).reduce(new BigDecimal(0), BigDecimal::add));
            hourEnergyConsumption.setPuck(v.stream().map(HourEnergyConsumption::getPuck).reduce(new BigDecimal(0), BigDecimal::add));
            hourEnergyConsumption.setCurling(v.stream().map(HourEnergyConsumption::getCurling).reduce(new BigDecimal(0), BigDecimal::add));
            hourEnergyConsumption.setTotal(v.stream().map(HourEnergyConsumption::getTotal).reduce(new BigDecimal(0), BigDecimal::add));
            if (k == 0) {
                hourEnergyConsumption.setSlip(yesterdayData.stream().map(HourEnergyConsumption::getSlip).reduce(new BigDecimal(0), BigDecimal::add).add(v.stream().map(HourEnergyConsumption::getSlip).reduce(new BigDecimal(0), BigDecimal::add)));
                hourEnergyConsumption.setPuck(yesterdayData.stream().map(HourEnergyConsumption::getPuck).reduce(new BigDecimal(0), BigDecimal::add).add(v.stream().map(HourEnergyConsumption::getPuck).reduce(new BigDecimal(0), BigDecimal::add)));
                hourEnergyConsumption.setCurling(yesterdayData.stream().map(HourEnergyConsumption::getCurling).reduce(new BigDecimal(0), BigDecimal::add).add(v.stream().map(HourEnergyConsumption::getCurling).reduce(new BigDecimal(0), BigDecimal::add)));
                hourEnergyConsumption.setTotal(yesterdayData.stream().map(HourEnergyConsumption::getTotal).reduce(new BigDecimal(0), BigDecimal::add).add(v.stream().map(HourEnergyConsumption::getTotal).reduce(new BigDecimal(0), BigDecimal::add)));
            }
            result.add(hourEnergyConsumption);
        });
        return result;
    }

    @Override
    public List<HourEnergyConsumption> selectByExample(Example example) {
        return hourEnergyConsumptionMapper.selectByExample(example);
    }



}
