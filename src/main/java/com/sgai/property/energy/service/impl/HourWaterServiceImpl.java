package com.sgai.property.energy.service.impl;

import com.sgai.property.energy.entity.HourElectric;
import com.sgai.property.energy.entity.HourWater;
import com.sgai.property.energy.service.HourWaterService;
import com.sgai.property.reformer.entity.HourWaterConsumption;
import com.szx.core.service.AbstractMapperService;
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
    private String puckMeterCode = "BQG";
    private String curlingMeterCode = "BHG";
    private String slipMeterCode = "HHSHG";
    @Override
    public HourWaterConsumption getRecentDataByMeterCode() {
        HourWaterConsumption hourWaterConsumption = new HourWaterConsumption();
        hourWaterConsumption.setPuck(getByMeterCode(puckMeterCode));
        hourWaterConsumption.setCurling(getByMeterCode(curlingMeterCode));
        hourWaterConsumption.setSlip(getByMeterCode(slipMeterCode));
        hourWaterConsumption.summary();
        return hourWaterConsumption;
    }
    private BigDecimal getByMeterCode(String meterCode) {
        try {
            LocalDateTime localDateTime = LocalDateTime.now().withMinute(0).withSecond(0).withNano(0);
            Example example = new Example(HourElectric.class);
            example.createCriteria().andEqualTo("recordTime", localDateTime).andEqualTo("meterCode", meterCode);
            return selectOneByExample(example).getRecordValue();
        }catch (Exception e){
            e.printStackTrace();
            return BigDecimal.ZERO;
        }

    }
}
