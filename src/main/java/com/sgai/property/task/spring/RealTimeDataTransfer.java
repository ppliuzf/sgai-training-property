package com.sgai.property.task.spring;

import com.sgai.property.alm.vo.Packing;
import com.sgai.property.mq.Sender;
import com.sgai.property.reformer.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * 实时数据中转站.
 *
 * @author ppliu
 * created in 2019/1/15 10:24
 */
@Component
@Lazy(false)
public class RealTimeDataTransfer {
    private static final long INTERVAL = 15 * 1000;
    @Autowired
    SpeedSkatingService speedSkatingService;
    @Autowired
    ParkingGarageService parkingGarageService;
    @Autowired
    private Sender sender;

    @Scheduled(fixedRate = INTERVAL)
    public void dataTransfer() throws IOException {
        Packing packing = parkingGarageService.getData();
        sender.sendPackingMessage(packing.toString());
    }
}
