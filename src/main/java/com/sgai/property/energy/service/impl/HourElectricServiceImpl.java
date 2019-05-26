package com.sgai.property.energy.service.impl;

import com.sgai.property.energy.entity.HourElectric;
import com.sgai.property.energy.service.HourElectricService;
import com.sgai.property.reformer.entity.HourEnergyConsumption;
import com.szx.core.service.AbstractMapperService;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 描述:
 *
 * @author ppliu
 * created in 2019/5/23 19:02
 */
@Service
public class HourElectricServiceImpl extends AbstractMapperService<HourElectric> implements HourElectricService {
    private String puckMeterCode = "BQG";
    private String curlingMeterCode = "BHG";
    private String slipMeterCode = "HHSHG";

    @Override
    public HourEnergyConsumption getRecentDataByMeterCode() {
        HourEnergyConsumption hourEnergyConsumption = new HourEnergyConsumption();
        hourEnergyConsumption.setPuck(getByMeterCode(puckMeterCode));
        hourEnergyConsumption.setCurling(getByMeterCode(curlingMeterCode));
        hourEnergyConsumption.setSlip(getByMeterCode(slipMeterCode));
        hourEnergyConsumption.summary();

        return hourEnergyConsumption;
    }

    private BigDecimal getByMeterCode(String meterCode) {
        try {
            LocalDateTime localDateTime = LocalDateTime.now().withMinute(0).withSecond(0).withNano(0);
            Example example = new Example(HourElectric.class);
            example.createCriteria().andEqualTo("recordTime", localDateTime).andEqualTo("meterCode", meterCode);
            return selectOneByExample(example).getRecordValue();
        } catch (Exception e) {
            e.printStackTrace();
            return BigDecimal.ZERO;
        }
    }
}
