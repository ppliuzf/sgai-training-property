package com.sgai.property.reformer.service.impl;

import com.sgai.property.reformer.entity.DayEnergyConsumption;
import com.sgai.property.reformer.service.DayEnergyConsumptionService;
import com.sgai.property.util.DateUtil;
import com.szx.core.service.AbstractMapperService;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author ppliu
 * created in 2019/1/22 10:11
 */
@Service
public class DayEnergyConsumptionServiceImpl extends AbstractMapperService<DayEnergyConsumption> implements DayEnergyConsumptionService {
    private List<LocalDate> dateList = DateUtil.getRecentDay(7);

    public List<DayEnergyConsumption> getRecent() {
        Example example = new Example(DayEnergyConsumption.class);
        example.createCriteria().andIn("recordTime", dateList);
        return selectByExample(example);
    }

    @Override
    public List<DayEnergyConsumption> getLast7DaysData() {
        List<DayEnergyConsumption> dayList = getRecent();
        Map<LocalDate, DayEnergyConsumption> map = dayList.stream().collect(Collectors.toMap(DayEnergyConsumption::getRecordTime, a -> a));
        dateList.forEach(date -> {
            if (map.get(date) == null) {
                map.put(date, createByDate(date));
            }
        });
        return map.values().stream().sorted(Comparator.comparing(DayEnergyConsumption::getRecordTime)).collect(Collectors.toList());
    }

    private DayEnergyConsumption createByDate(LocalDate date) {
        DayEnergyConsumption dayEnergyConsumption = new DayEnergyConsumption();
        dayEnergyConsumption.setTotal(BigDecimal.ZERO);
        dayEnergyConsumption.setPuck(BigDecimal.ZERO);
        dayEnergyConsumption.setPuckDrainage(BigDecimal.ZERO);
        dayEnergyConsumption.setPuckElevator(BigDecimal.ZERO);
        dayEnergyConsumption.setPuckIllumination(BigDecimal.ZERO);
        dayEnergyConsumption.setCurling(BigDecimal.ZERO);
        dayEnergyConsumption.setCurlingDrainage(BigDecimal.ZERO);
        dayEnergyConsumption.setCurlingElevator(BigDecimal.ZERO);
        dayEnergyConsumption.setCurlingIllumination(BigDecimal.ZERO);
        dayEnergyConsumption.setSlip(BigDecimal.ZERO);
        dayEnergyConsumption.setSlipDrainage(BigDecimal.ZERO);
        dayEnergyConsumption.setSlipElevator(BigDecimal.ZERO);
        dayEnergyConsumption.setSlipIllumination(BigDecimal.ZERO);
        dayEnergyConsumption.setRecordTime(date);
        return dayEnergyConsumption;
    }
}
