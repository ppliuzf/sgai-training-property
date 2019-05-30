package com.sgai.property.energy.service.impl;

import com.sgai.property.energy.entity.HourWater;
import com.sgai.property.energy.service.HourWaterService;
import com.sgai.property.reformer.entity.DayWaterConsumption;
import com.sgai.property.reformer.entity.HourWaterConsumption;
import com.sgai.property.reformer.service.DayWaterConsumptionService;
import com.sgai.property.reformer.service.HourWaterConsumptionService;
import com.szx.core.service.AbstractMapperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 描述:
 *
 * @author ppliu
 * created in 2019/5/23 19:03
 */
@Service
public class HourWaterServiceImpl extends AbstractMapperService<HourWater> implements HourWaterService {
    private static final String total = "DXZX";
    private static final String puck = "BQG";
    private static final String curling = "BHG";
    private static final String slip = "HHSHG";
    @Autowired
    private HourWaterConsumptionService hourWaterConsumptionService;
    @Autowired
    private DayWaterConsumptionService dayWaterConsumptionService;

    public HourWaterConsumption getRecentDataByMeterCode() {
        HourWaterConsumption hourWaterConsumption = new HourWaterConsumption();
        hourWaterConsumption.setTotal(getByMeterCode(total));
        hourWaterConsumption.setPuck(getByMeterCode(puck));
        hourWaterConsumption.setCurling(getByMeterCode(curling));
        hourWaterConsumption.setSlip(getByMeterCode(slip));
        hourWaterConsumption.setRecordTime(LocalDateTime.now().withSecond(0).withMinute(0).withNano(0));

        return hourWaterConsumption;
    }

    @Override
    public void record() {
        Example example = new Example(HourWaterConsumption.class);
        example.createCriteria().andEqualTo("recordTime", LocalDateTime.now().withSecond(0).withMinute(0).withNano(0));
        if (hourWaterConsumptionService.selectByExample(example).isEmpty()) {
            HourWaterConsumption hourWaterConsumption = getRecentDataByMeterCode();
            hourWaterConsumptionService.insertSelective(hourWaterConsumption);

            Example dayExample = new Example(DayWaterConsumption.class);
            dayExample.createCriteria().andEqualTo("recordTime", LocalDate.now());
            DayWaterConsumption dayWaterConsumption = dayWaterConsumptionService.selectOneByExample(dayExample);
            if (dayWaterConsumption == null) {
                dayWaterConsumption = new DayWaterConsumption();
                dayWaterConsumption.setPuck(hourWaterConsumption.getPuck());
                dayWaterConsumption.setRecordTime(hourWaterConsumption.getRecordTime().toLocalDate());
                dayWaterConsumption.setSlip(hourWaterConsumption.getSlip());
                dayWaterConsumption.setTotal(hourWaterConsumption.getTotal());
                dayWaterConsumption.setCurling(hourWaterConsumption.getCurling());
                dayWaterConsumptionService.insertSelective(dayWaterConsumption);
            } else {
                dayWaterConsumption.setPuck(dayWaterConsumption.getPuck().add(hourWaterConsumption.getPuck()));
                dayWaterConsumption.setTotal(dayWaterConsumption.getTotal().add(hourWaterConsumption.getTotal()));
                dayWaterConsumption.setSlip(dayWaterConsumption.getSlip().add(hourWaterConsumption.getSlip()));
                dayWaterConsumption.setCurling(dayWaterConsumption.getCurling().add(hourWaterConsumption.getCurling()));
                dayWaterConsumptionService.updateByPrimaryKeySelective(dayWaterConsumption);
            }
        }
    }

    private BigDecimal getByMeterCode(String meterCode) {
        Example example = new Example(HourWater.class);
        example.createCriteria()
                .andEqualTo("recordTime", LocalDateTime.now().withMinute(0).withSecond(0).withNano(0))
                .andEqualTo("meterCode", meterCode);
        HourWater hourElectric = selectOneByExample(example);
        return hourElectric == null ? BigDecimal.ZERO : hourElectric.getRecordValue();
    }
}
