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

/**
 * @author ppliu
 * created in 2019/1/22 10:14
 */
@Component
@Lazy(false)
@Transactional
public class RecordWaterTask {
    private static final long INTERVAL = 30 * 1000;
    @Autowired
    private HourWaterConsumptionService hourWaterConsumptionService;
    @Autowired
    private DayWaterConsumptionService dayWaterConsumptionService;
    @Autowired
    private HourWaterService hourWaterService;
    @Autowired
    private Sender sender;

    //每小时执行一次.
    @Scheduled(cron = "0 15,30,45 * * * ?")
    public void recordWater() throws IOException {
        hourWaterService.record();
        WaterVo waterVo = new WaterVo();
        waterVo.setWaterDay(dayWaterConsumptionService.getLast7DaysData());
        waterVo.setWaterHour(hourWaterConsumptionService.getLast7HoursData());
        sender.sendWaterMessage(waterVo.toString());
    }

}
