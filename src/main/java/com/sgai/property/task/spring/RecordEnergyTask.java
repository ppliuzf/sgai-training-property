package com.sgai.property.task.spring;

import com.sgai.property.alm.vo.EnergyVo;
import com.sgai.property.energy.service.HourElectricService;
import com.sgai.property.mq.Sender;
import com.sgai.property.reformer.service.DayEnergyConsumptionService;
import com.sgai.property.reformer.service.HourEnergyConsumptionService;
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
public class RecordEnergyTask {
    private static final long INTERVAL = 300 * 1000;
    @Autowired
    private HourEnergyConsumptionService hourEnergyConsumptionService;
    @Autowired
    private DayEnergyConsumptionService dayEnergyConsumptionService;
    @Autowired
    private HourElectricService hourElectricService;
    @Autowired
    private Sender sender;

    //每小时执行一次.
    @Scheduled(fixedRate = INTERVAL)
    public void recordEnergy() throws IOException {
        LocalDateTime localDateTime = LocalDateTime.now().withMinute(0).withSecond(0).withNano(0);
        hourElectricService.record(localDateTime);
        EnergyVo energyVo = new EnergyVo();
        energyVo.setEnergyDay(dayEnergyConsumptionService.getLast7DaysData(localDateTime.toLocalDate()));
        energyVo.setEnergyHour(hourEnergyConsumptionService.getLast7HoursData(localDateTime));
        sender.sendEnergyMessage(energyVo.toString());
    }
}
