package com.sgai.property.energy.service.impl;

import com.sgai.property.energy.entity.HourElectric;
import com.sgai.property.energy.service.HourElectricService;
import com.sgai.property.reformer.entity.DayEnergyConsumption;
import com.sgai.property.reformer.entity.HourEnergyConsumption;
import com.sgai.property.reformer.service.DayEnergyConsumptionService;
import com.sgai.property.reformer.service.HourEnergyConsumptionService;
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
 * created in 2019/5/23 19:02
 */
@Service
public class HourElectricServiceImpl extends AbstractMapperService<HourElectric> implements HourElectricService {
    @Autowired
    private HourEnergyConsumptionService hourEnergyConsumptionService;
    @Autowired
    private DayEnergyConsumptionService dayEnergyConsumptionService;
    private static final String total = "DXZX";
    private static final String puck = "BQG";
    private static final String curling = "BHG";
    private static final String slip = "HHSHG";

    private static final String puckDrainage = "BQG_GPS";
    private static final String puckIllumination = "BQG_PTZM";
    private static final String puckElevator = "BQG_DTXT";

    private static final String curlingDrainage = "BHG_GPS";
    private static final String curlingIllumination = "BHG_PTZM";
    private static final String curlingElevator = "BHG_DTXT";

    private static final String slipDrainage = "HSG_GPS";
    private static final String slipIllumination = "HSG_PTZM";
    private static final String slipElevator = "HSG_DTXT";

    public HourEnergyConsumption getRecentDataByMeterCode() {
        HourEnergyConsumption hourEnergyConsumption = new HourEnergyConsumption();
        hourEnergyConsumption.setTotal(getByMeterCode(total));
        hourEnergyConsumption.setPuck(getByMeterCode(puck));
        hourEnergyConsumption.setCurling(getByMeterCode(curling));
        hourEnergyConsumption.setSlip(getByMeterCode(slip));
        hourEnergyConsumption.setPuckDrainage(getByMeterCode(puckDrainage));
        hourEnergyConsumption.setPuckElevator(getByMeterCode(puckElevator));
        hourEnergyConsumption.setPuckIllumination(getByMeterCode(puckIllumination));
        hourEnergyConsumption.setCurlingDrainage(getByMeterCode(curlingDrainage));
        hourEnergyConsumption.setCurlingElevator(getByMeterCode(curlingElevator));
        hourEnergyConsumption.setCurlingIllumination(getByMeterCode(curlingIllumination));
        hourEnergyConsumption.setSlipDrainage(getByMeterCode(slipDrainage));
        hourEnergyConsumption.setSlipElevator(getByMeterCode(slipElevator));
        hourEnergyConsumption.setSlipIllumination(getByMeterCode(slipIllumination));
        hourEnergyConsumption.setRecordTime(LocalDateTime.now().withSecond(0).withMinute(0).withNano(0));

        return hourEnergyConsumption;
    }

    @Override
    public void record() {
        Example example = new Example(HourEnergyConsumption.class);
        example.createCriteria().andEqualTo("recordTime", LocalDateTime.now().withSecond(0).withMinute(0).withNano(0));
        if (hourEnergyConsumptionService.selectByExample(example).isEmpty()) {
            HourEnergyConsumption hourEnergyConsumption = getRecentDataByMeterCode();
            hourEnergyConsumptionService.insert(hourEnergyConsumption);

            Example dayExample = new Example(DayEnergyConsumption.class);
            dayExample.createCriteria().andEqualTo("recordTime", LocalDate.now());
            DayEnergyConsumption dayEnergyConsumption = dayEnergyConsumptionService.selectOneByExample(dayExample);
            if (dayEnergyConsumption == null) {
                dayEnergyConsumption = new DayEnergyConsumption();
                dayEnergyConsumption.setRecordTime(hourEnergyConsumption.getRecordTime().toLocalDate());
                dayEnergyConsumption.setTotal(hourEnergyConsumption.getTotal());
                dayEnergyConsumption.setPuck(hourEnergyConsumption.getPuck());
                dayEnergyConsumption.setCurling(hourEnergyConsumption.getCurling());
                dayEnergyConsumption.setSlip(hourEnergyConsumption.getSlip());
                dayEnergyConsumption.setPuckDrainage(hourEnergyConsumption.getPuckDrainage());
                dayEnergyConsumption.setPuckIllumination(hourEnergyConsumption.getPuckIllumination());
                dayEnergyConsumption.setPuckElevator(hourEnergyConsumption.getPuckDrainage());
                dayEnergyConsumption.setCurlingDrainage(hourEnergyConsumption.getCurlingDrainage());
                dayEnergyConsumption.setCurlingElevator(hourEnergyConsumption.getCurlingElevator());
                dayEnergyConsumption.setCurlingIllumination(hourEnergyConsumption.getCurlingIllumination());
                dayEnergyConsumption.setSlipIllumination(hourEnergyConsumption.getSlipIllumination());
                dayEnergyConsumption.setSlipElevator(hourEnergyConsumption.getSlipElevator());
                dayEnergyConsumption.setSlipDrainage(hourEnergyConsumption.getSlipDrainage());
                dayEnergyConsumptionService.insertSelective(dayEnergyConsumption);
            } else {
                dayEnergyConsumption.setTotal(dayEnergyConsumption.getTotal().add(hourEnergyConsumption.getTotal()));
                dayEnergyConsumption.setPuck(dayEnergyConsumption.getPuck().add(hourEnergyConsumption.getPuck()));
                dayEnergyConsumption.setCurling(dayEnergyConsumption.getCurling().add(hourEnergyConsumption.getCurling()));
                dayEnergyConsumption.setSlip(dayEnergyConsumption.getSlip().add(hourEnergyConsumption.getSlip()));
                dayEnergyConsumption.setPuckDrainage(dayEnergyConsumption.getPuckDrainage().add(hourEnergyConsumption.getPuckDrainage()));
                dayEnergyConsumption.setPuckIllumination(dayEnergyConsumption.getPuckIllumination().add(hourEnergyConsumption.getPuckIllumination()));
                dayEnergyConsumption.setPuckElevator(dayEnergyConsumption.getPuckDrainage().add(hourEnergyConsumption.getPuckDrainage()));
                dayEnergyConsumption.setCurlingDrainage(dayEnergyConsumption.getCurlingDrainage().add(hourEnergyConsumption.getCurlingDrainage()));
                dayEnergyConsumption.setCurlingElevator(dayEnergyConsumption.getCurlingElevator().add(hourEnergyConsumption.getCurlingElevator()));
                dayEnergyConsumption.setCurlingIllumination(dayEnergyConsumption.getCurlingIllumination().add(hourEnergyConsumption.getCurlingIllumination()));
                dayEnergyConsumption.setSlipIllumination(dayEnergyConsumption.getSlipIllumination().add(hourEnergyConsumption.getSlipIllumination()));
                dayEnergyConsumption.setSlipElevator(dayEnergyConsumption.getSlipElevator().add(hourEnergyConsumption.getSlipElevator()));
                dayEnergyConsumption.setSlipDrainage(dayEnergyConsumption.getSlipDrainage().add(hourEnergyConsumption.getSlipDrainage()));
                dayEnergyConsumptionService.updateByPrimaryKeySelective(dayEnergyConsumption);
            }
        }
    }

    private BigDecimal getByMeterCode(String meterCode) {
        Example example = new Example(HourElectric.class);
        example.createCriteria()
                .andEqualTo("recordTime", LocalDateTime.now().withMinute(0).withSecond(0).withNano(0))
                .andEqualTo("meterCode", meterCode);
        HourElectric hourElectric = selectOneByExample(example);
        return hourElectric == null ? BigDecimal.ZERO : hourElectric.getRecordValue();
    }
}
