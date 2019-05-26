package com.sgai.property.socket.service.impl;

import com.sgai.property.alm.service.AlmDockingDataService;
import com.sgai.property.alm.vo.ScreenVo;
import com.sgai.property.park.dto.IntervalRecord;
import com.sgai.property.park.service.InOutRecordService;
import com.sgai.property.reformer.service.*;
import com.sgai.property.socket.service.SocketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.stream.Collectors;

/**
 * @author ppliu
 * created in 2019/1/26 15:26
 */
@Service
public class SocketServiceImpl implements SocketService {
    @Autowired
    private AlmDockingDataService almDockingDataService;
    @Autowired
    private OverviewService overviewService;

    @Autowired
    private CurlingHallService curlingHallService;
    @Autowired
    private IceHockeyHallService iceHockeyHallService;
    @Autowired
    private SpeedSkatingService speedSkatingService;
    @Autowired
    private ParkingGarageService parkingGarageService;
    @Autowired
    private IceMakerService iceMakerService;
    @Autowired
    private InOutRecordService inOutRecordService;
    @Autowired
    private DayEnergyConsumptionService dayEnergyConsumptionService;
    @Autowired
    private HourEnergyConsumptionService hourEnergyConsumptionService;
    @Autowired
    private DayWaterService dayWaterService;

    /**
     * 获取 报警数据.
     */
    @Override
    public String getAlmData() {
        ScreenVo screenVo = new ScreenVo();
        screenVo.setType(1);
        screenVo.setAlmDockingData(almDockingDataService.getRecentList(5));
        return screenVo.toString();
    }

    /**
     * 获取 obix站点数据.
     */
    @Override
    public String getObixData() {
        ScreenVo screenVo = new ScreenVo();
        screenVo.setType(2);
        screenVo.setOverview(overviewService.getData());
        screenVo.setCurling(curlingHallService.getData());
        screenVo.setPuck(iceHockeyHallService.getData());
        screenVo.setSlip(speedSkatingService.getData());
        try {
            screenVo.setPacking(parkingGarageService.getData());
        }catch (Exception e){
        }
        screenVo.setIceMakerList(iceMakerService.getAllData());
        return screenVo.toString();
    }

    /**
     * 获取 进出场数据.
     */
    @Override
    public String getInOutData() {
        ScreenVo screenVo = new ScreenVo();
        screenVo.setType(3);
        screenVo.setIntervalOrderTime(inOutRecordService.getIntervalRecordToday().stream().sorted(Comparator.comparing(IntervalRecord::getTimeSorter)).collect(Collectors.toList()));
        screenVo.setIntervalOrderFlow(inOutRecordService.getIntervalRecordToday().stream().sorted(Comparator.comparing(IntervalRecord::getFlow).reversed()).limit(5).collect(Collectors.toList()));
        return screenVo.toString();
    }

    /**
     * 获取 能源数据.
     */
    @Override
    public String getEnergyData() {
        ScreenVo screenVo = new ScreenVo();
        screenVo.setType(4);
        // 查询
        screenVo.setEnergyDay(dayEnergyConsumptionService.selectByRecentDay(7));
        screenVo.setEnergyHour(hourEnergyConsumptionService.selectOneDay());
        screenVo.setEnergyToday(dayEnergyConsumptionService.getTodayData());
        screenVo.setEnergyYesterday(dayEnergyConsumptionService.getYesterdayData());
        return screenVo.toString();
    }

    /**
     * 获取 水耗数据.
     */
    @Override
    public String getWaterData() {
        ScreenVo screenVo = new ScreenVo();
        screenVo.setType(5);
        screenVo.setWaterToday((dayWaterService.getTodayData()));
        screenVo.setWaterYesterday(dayWaterService.getYesterdayData());
        screenVo.setWaterDay(dayWaterService.selectByRecentDay(7));
        return screenVo.toString();
    }
}
