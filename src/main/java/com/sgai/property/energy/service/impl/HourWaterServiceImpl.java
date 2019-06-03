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
import java.time.LocalDateTime;

/**
 * 描述:
 *
 * @author ppliu
 * created in 2019/5/23 19:03
 */
@Service
public class HourWaterServiceImpl extends AbstractMapperService<HourWater> implements HourWaterService {
    private LocalDateTime localDateTime;
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
        hourWaterConsumption.setRecordTime(this.localDateTime);

        return hourWaterConsumption;
    }

    @Override
    public void record(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
        Example example = new Example(HourWaterConsumption.class);
        example.createCriteria().andEqualTo("recordTime", this.localDateTime);
        HourWaterConsumption hourWaterConsumption = getRecentDataByMeterCode();
        Example dayExample = new Example(DayWaterConsumption.class);
        dayExample.createCriteria().andEqualTo("recordTime",this.localDateTime.toLocalDate());
        DayWaterConsumption dayWaterConsumption = dayWaterConsumptionService.selectOneByExample(dayExample);
        if (hourWaterConsumptionService.selectByExample(example).isEmpty()) {
            hourWaterConsumptionService.insertSelective(hourWaterConsumption);
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
        } else {
            HourWaterConsumption original = hourWaterConsumptionService.selectOneByExample(example);
            dayWaterConsumption.setTotal(dayWaterConsumption.getTotal().subtract(original.getTotal()).add(hourWaterConsumption.getTotal()));
            dayWaterConsumption.setCurling(dayWaterConsumption.getCurling().subtract(original.getCurling()).add(hourWaterConsumption.getCurling()));
            dayWaterConsumption.setSlip(dayWaterConsumption.getSlip().subtract(original.getSlip()).add(hourWaterConsumption.getSlip()));
            dayWaterConsumption.setPuck(dayWaterConsumption.getPuck().subtract(original.getPuck()).add(hourWaterConsumption.getPuck()));

            dayWaterConsumptionService.updateByPrimaryKeySelective(dayWaterConsumption);

            original.setTotal(hourWaterConsumption.getTotal());
            original.setPuck(hourWaterConsumption.getPuck());
            original.setSlip(hourWaterConsumption.getSlip());
            original.setCurling(hourWaterConsumption.getCurling());
            hourWaterConsumptionService.updateByPrimaryKeySelective(original);
        }
    }

    private BigDecimal getByMeterCode(String meterCode) {
        Example example = new Example(HourWater.class);
        example.createCriteria()
                .andEqualTo("recordTime", this.localDateTime)
                .andEqualTo("meterCode", meterCode);
        HourWater hourElectric = selectOneByExample(example);
        return hourElectric == null ? BigDecimal.ZERO : hourElectric.getRecordValue();
    }
}
