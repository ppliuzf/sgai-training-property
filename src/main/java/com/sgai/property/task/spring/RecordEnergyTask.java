package com.sgai.property.task.spring;

import com.sgai.property.alm.vo.EnergyVo;
import com.sgai.property.energy.service.HourElectricService;
import com.sgai.property.mq.Sender;
import com.sgai.property.reformer.entity.DayEnergyConsumption;
import com.sgai.property.reformer.entity.HourEnergyConsumption;
import com.sgai.property.reformer.service.DayEnergyConsumptionService;
import com.sgai.property.reformer.service.HourEnergyConsumptionService;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author ppliu
 * created in 2019/1/22 10:14
 */
@Component
@Lazy(false)
@Transactional
public class RecordEnergyTask {
    @Autowired
    private HourEnergyConsumptionService hourEnergyConsumptionService;
    @Autowired
    private DayEnergyConsumptionService dayEnergyConsumptionService;
    @Autowired
    private Sender sender;
    @Autowired
    private HourElectricService hourElectricService;
    private static final int RECENT_HOURS = 24;
    private static final int RECENT_DAYS = 7;

    //每小时执行一次.
    @Scheduled(cron = "0 15 * * * ?")
    public void recordEnergy() throws IOException {
        //每小时调用一次并保存记录.
        HourEnergyConsumption hourEnergyConsumption = hourElectricService.getRecentDataByMeterCode();
        hourEnergyConsumptionService.insertSelective(hourEnergyConsumption);
        //计算并存储到day表中.
        resolveDayTable(hourEnergyConsumption);
        // 查询
        List<HourEnergyConsumption> hourEnergyConsumptionList = hourEnergyConsumptionService.selectOneDay();
        hourEnergyConsumptionList = completionHours(hourEnergyConsumptionList);
        List<DayEnergyConsumption> dayEnergyConsumptionList = dayEnergyConsumptionService.selectByRecentDay(RECENT_DAYS);
        dayEnergyConsumptionList = completionDays(dayEnergyConsumptionList);


        // 发送到mq
        EnergyVo energyVo = new EnergyVo();
        energyVo.setEnergyDay(dayEnergyConsumptionList);
        energyVo.setEnergyHour(hourEnergyConsumptionList);
        sender.sendEnergyMessage(energyVo.toString());
    }

    private List<DayEnergyConsumption> completionDays(List<DayEnergyConsumption> dayList) {
        Map<String, DayEnergyConsumption> map = dayList
                .stream()
                .collect(Collectors.toMap(dayEnergyConsumption -> DateFormatUtils.format(dayEnergyConsumption.getRecordTime(), "yyyy/MM/dd"), d -> d, (k1, k2) -> k1));
        Calendar calendar = Calendar.getInstance();
        if (map.get(DateFormatUtils.format(calendar.getTime(), "yyyy/MM/dd")) == null) {
            createDay(calendar.getTime(), map);
        }
        calendar.add(Calendar.DAY_OF_YEAR, -1);
        if (map.get(DateFormatUtils.format(calendar.getTime(), "yyyy/MM/dd")) == null) {
            createDay(calendar.getTime(), map);
        }
        calendar.add(Calendar.DAY_OF_YEAR, -1);
        if (map.get(DateFormatUtils.format(calendar.getTime(), "yyyy/MM/dd")) == null) {
            createDay(calendar.getTime(), map);
        }
        calendar.add(Calendar.DAY_OF_YEAR, -1);
        if (map.get(DateFormatUtils.format(calendar.getTime(), "yyyy/MM/dd")) == null) {
            createDay(calendar.getTime(), map);
        }
        calendar.add(Calendar.DAY_OF_YEAR, -1);
        if (map.get(DateFormatUtils.format(calendar.getTime(), "yyyy/MM/dd")) == null) {
            createDay(calendar.getTime(), map);
        }
        calendar.add(Calendar.DAY_OF_YEAR, -1);
        if (map.get(DateFormatUtils.format(calendar.getTime(), "yyyy/MM/dd")) == null) {
            createDay(calendar.getTime(), map);
        }
        calendar.add(Calendar.DAY_OF_YEAR, -1);
        if (map.get(DateFormatUtils.format(calendar.getTime(), "yyyy/MM/dd")) == null) {
            createDay(calendar.getTime(), map);
        }
        return new ArrayList<>(map.values())
                .stream()
                .sorted(Comparator.comparing(DayEnergyConsumption::getRecordTime))
                .collect(Collectors.toList());
    }


    private List<HourEnergyConsumption> completionHours(List<HourEnergyConsumption> hourList) {
        Map<Integer, HourEnergyConsumption> map = hourList.stream().collect(Collectors.toMap(hourEnergyConsumption -> {
            Date date = hourEnergyConsumption.getRecordTime();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            return calendar.get(Calendar.HOUR_OF_DAY);
        }, d -> d, (k1, k2) -> k1));
        if (map.get(0) == null) {
            createHour(0, map);
        }
        if (map.get(3) == null) {
            createHour(3, map);
        }
        if (map.get(6) == null) {
            createHour(6, map);
        }
        if (map.get(9) == null) {
            createHour(9, map);
        }
        if (map.get(12) == null) {
            createHour(12, map);
        }
        if (map.get(15) == null) {
            createHour(15, map);
        }
        if (map.get(18) == null) {
            createHour(18, map);
        }
        if (map.get(21) == null) {
            createHour(21, map);
        }
        return new ArrayList<>(map.values())
                .stream()
                .sorted(Comparator.comparing(HourEnergyConsumption::getRecordTime))
                .collect(Collectors.toList());
    }

    private void createHour(int hour, Map<Integer, HourEnergyConsumption> map) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        map.put(hour, HourEnergyConsumption.emptyRecordWithDate(calendar.getTime()));
    }

    private void createDay(Date time, Map<String, DayEnergyConsumption> map) {
        map.put(DateFormatUtils.format(time, "yyyy/MM/dd"), DayEnergyConsumption.emptyRecordWithDate(time));
    }

    /**
     * 记录日程表.
     */
    public void resolveDayTable(HourEnergyConsumption hourEnergyConsumption) {
        Example dayExample = new Example(DayEnergyConsumption.class);
        dayExample.createCriteria().andCondition("to_days(record_time) = to_days(now())");
        DayEnergyConsumption dayEnergyConsumption = dayEnergyConsumptionService.selectOnByExample(dayExample);
        if (dayEnergyConsumption == null) {
            dayEnergyConsumption = new DayEnergyConsumption();
            BeanUtils.copyProperties(hourEnergyConsumption, dayEnergyConsumption);
            dayEnergyConsumption.setId(null);
            dayEnergyConsumptionService.insertSelective(dayEnergyConsumption);
        } else {
            dayEnergyConsumption.setTotal(dayEnergyConsumption.getTotal().add(hourEnergyConsumption.getTotal()));
            dayEnergyConsumption.setCurling(dayEnergyConsumption.getCurling().add(hourEnergyConsumption.getCurling()));
            dayEnergyConsumption.setPuck(dayEnergyConsumption.getPuck().add(hourEnergyConsumption.getPuck()));
            dayEnergyConsumption.setSlip(dayEnergyConsumption.getSlip().add(hourEnergyConsumption.getSlip()));
            dayEnergyConsumptionService.updateByPrimaryKeySelective(dayEnergyConsumption);
        }
    }
}
