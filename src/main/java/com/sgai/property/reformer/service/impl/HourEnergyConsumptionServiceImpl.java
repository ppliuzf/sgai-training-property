package com.sgai.property.reformer.service.impl;

import com.sgai.property.reformer.entity.HourEnergyConsumption;
import com.sgai.property.reformer.service.HourEnergyConsumptionService;
import com.sgai.property.util.DateUtil;
import com.szx.core.service.AbstractMapperService;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author ppliu
 * created in 2019/1/22 10:07
 */
@Service
public class HourEnergyConsumptionServiceImpl extends AbstractMapperService<HourEnergyConsumption> implements HourEnergyConsumptionService {
    private List<LocalDateTime> timeList = DateUtil.getRecentTime(24);

    public List<HourEnergyConsumption> getRecent() {
        Example example = new Example(HourEnergyConsumption.class);
        example.createCriteria().andIn("recordTime", timeList);
        return selectByExample(example);
    }

    @Override
    public List<HourEnergyConsumption> getLast7HoursData() {
        List<HourEnergyConsumption> hourList = getRecent();
        Map<LocalDateTime, HourEnergyConsumption> map = hourList.stream()
                .collect(Collectors.toMap(HourEnergyConsumption::getRecordTime, a -> a, (k1, k2) -> k1));
        timeList.forEach(time -> {
            if (map.get(time) == null) {
                map.put(time, createByTime(time));
            }
        });
        return map.values()
                .stream()
                .filter(hourData -> hourData.getRecordTime().getHour() % 3 == 0)
                .sorted(Comparator.comparing(HourEnergyConsumption::getRecordTime))
                .collect(Collectors.toList());
    }

    private HourEnergyConsumption createByTime(LocalDateTime time) {
        HourEnergyConsumption hourEnergyConsumption = new HourEnergyConsumption();
        hourEnergyConsumption.setTotal(BigDecimal.ZERO);
        hourEnergyConsumption.setPuck(BigDecimal.ZERO);
        hourEnergyConsumption.setPuckDrainage(BigDecimal.ZERO);
        hourEnergyConsumption.setPuckElevator(BigDecimal.ZERO);
        hourEnergyConsumption.setPuckIllumination(BigDecimal.ZERO);
        hourEnergyConsumption.setCurling(BigDecimal.ZERO);
        hourEnergyConsumption.setCurlingDrainage(BigDecimal.ZERO);
        hourEnergyConsumption.setCurlingElevator(BigDecimal.ZERO);
        hourEnergyConsumption.setCurlingIllumination(BigDecimal.ZERO);
        hourEnergyConsumption.setSlip(BigDecimal.ZERO);
        hourEnergyConsumption.setSlipDrainage(BigDecimal.ZERO);
        hourEnergyConsumption.setSlipElevator(BigDecimal.ZERO);
        hourEnergyConsumption.setSlipIllumination(BigDecimal.ZERO);
        hourEnergyConsumption.setRecordTime(time);
        return hourEnergyConsumption;
    }
}
