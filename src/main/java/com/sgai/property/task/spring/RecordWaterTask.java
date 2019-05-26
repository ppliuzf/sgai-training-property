package com.sgai.property.task.spring;

import com.sgai.property.alm.vo.WaterVo;
import com.sgai.property.energy.service.HourWaterService;
import com.sgai.property.mq.Sender;
import com.sgai.property.reformer.entity.DayWater;
import com.sgai.property.reformer.entity.HourWaterConsumption;
import com.sgai.property.reformer.service.DayWaterService;
import com.sgai.property.reformer.service.HourWaterConsumptionService;
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
public class RecordWaterTask {
    @Autowired
    private HourWaterConsumptionService hourWaterConsumptionService;
    @Autowired
    private DayWaterService dayWaterService;
    @Autowired
    private Sender sender;
    private static final int RECENT_DAYS = 7;
    @Autowired
    private HourWaterService hourWaterService;

    //每小时执行一次.
    @Scheduled(cron = "0 15 * * * ?")
    public void recordWater() throws IOException {
        //每小时调用一次并保存记录.
        HourWaterConsumption hourWaterConsumption = hourWaterService.getRecentDataByMeterCode();
        hourWaterConsumptionService.insertSelective(hourWaterConsumption);
        //计算并存储到day表中.
        resolveDayTable(hourWaterConsumption);
        // 查询
        List<HourWaterConsumption> hourWaterConsumptionList = hourWaterConsumptionService.selectOneDay();
        hourWaterConsumptionList = completionHours(hourWaterConsumptionList);
        List<DayWater> dayWaterList = dayWaterService.selectByRecentDay(RECENT_DAYS);
        dayWaterList = completionDays(dayWaterList);

        // 发送到mq
        WaterVo waterVo = new WaterVo();
        waterVo.setWaterDay(dayWaterList);
        waterVo.setWaterHour(hourWaterConsumptionList);
        sender.sendWaterMessage(waterVo.toString());
    }

    private List<HourWaterConsumption> completionHours(List<HourWaterConsumption> hourList) {
        Map<Integer, HourWaterConsumption> map = hourList.stream().collect(Collectors.toMap(hourEnergyConsumption -> {
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
                .sorted(Comparator.comparing(HourWaterConsumption::getRecordTime))
                .collect(Collectors.toList());
    }

    private List<DayWater> completionDays(List<DayWater> dayList) {
        Map<String, DayWater> map = dayList
                .stream()
                .collect(Collectors.toMap(dayWater -> DateFormatUtils.format(dayWater.getRecordTime(), "yyyy/MM/dd"), d -> d, (k1, k2) -> k1));
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
                .sorted(Comparator.comparing(DayWater::getRecordTime))
                .collect(Collectors.toList());
    }

    /**
     * 记录日程表.
     */
    public void resolveDayTable(HourWaterConsumption hourWaterConsumption) {
        Example dayExample = new Example(DayWater.class);
        dayExample.createCriteria().andCondition("to_days(record_time) = to_days(now())");
        DayWater dayWater = dayWaterService.selectOnByExample(dayExample);
        if (dayWater == null) {
            dayWater = new DayWater();
            BeanUtils.copyProperties(hourWaterConsumption, dayWater);
            dayWater.setId(null);
            dayWaterService.insertSelective(dayWater);
        } else {
            dayWater.setCurling(dayWater.getCurling().add(hourWaterConsumption.getCurling()));
            dayWater.setTotal(dayWater.getTotal().add(hourWaterConsumption.getTotal()));
            dayWater.setPuck(dayWater.getPuck().add(hourWaterConsumption.getPuck()));
            dayWater.setSlip(dayWater.getSlip().add(hourWaterConsumption.getSlip()));
            dayWaterService.updateByPrimaryKeySelective(dayWater);
        }
    }

    private void createHour(int hour, Map<Integer, HourWaterConsumption> map) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        map.put(hour, HourWaterConsumption.emptyRecordWithDate(calendar.getTime()));
    }
    private void createDay(Date time, Map<String, DayWater> map) {
        map.put(DateFormatUtils.format(time, "yyyy/MM/dd"), DayWater.emptyRecordWithDate(time));
    }
}
