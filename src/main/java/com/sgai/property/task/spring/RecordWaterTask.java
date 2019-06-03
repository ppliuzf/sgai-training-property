package com.sgai.property.task.spring;

import com.sgai.property.alm.vo.WaterVo;
import com.sgai.property.energy.service.HourWaterService;
import com.sgai.property.mq.Sender;
import com.sgai.property.reformer.service.DayWaterConsumptionService;
import com.sgai.property.reformer.service.HourWaterConsumptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.time.LocalDateTime;

/**
 * @author ppliu
 * created in 2019/1/22 10:14
 */
@Component
@Lazy(false)
@Transactional
public class RecordWaterTask {
    private static final long INTERVAL = 300 * 1000;
    @Autowired
    private HourWaterConsumptionService hourWaterConsumptionService;
    @Autowired
    private DayWaterConsumptionService dayWaterConsumptionService;
    @Autowired
    private HourWaterService hourWaterService;
    @Autowired
    private Sender sender;

    //每小时执行一次.
    @Scheduled(fixedRate = INTERVAL)
    public void recordWater() throws IOException {
        LocalDateTime localDateTime = LocalDateTime.now().withMinute(0).withSecond(0).withNano(0);
        hourWaterService.record(localDateTime);
        WaterVo waterVo = new WaterVo();
        waterVo.setWaterDay(dayWaterConsumptionService.getLast7DaysData(localDateTime.toLocalDate()));
        waterVo.setWaterHour(hourWaterConsumptionService.getLast7HoursData(localDateTime));
        sender.sendWaterMessage(waterVo.toString());
    }

}
