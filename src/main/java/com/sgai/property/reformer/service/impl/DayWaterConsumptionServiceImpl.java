package com.sgai.property.reformer.service.impl;

import com.sgai.property.reformer.entity.DayWaterConsumption;
import com.sgai.property.reformer.service.DayWaterConsumptionService;
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
 * created in 2019/1/23 15:01
 */
@Service
public class DayWaterConsumptionServiceImpl extends AbstractMapperService<DayWaterConsumption> implements DayWaterConsumptionService {
    private List<LocalDate> dateList = DateUtil.getRecentDay(7);

    public List<DayWaterConsumption> getRecent() {
        Example example = new Example(DayWaterConsumption.class);
        example.createCriteria().andIn("recordTime", dateList);
        return selectByExample(example);
    }

    @Override
    public List<DayWaterConsumption> getLast7DaysData() {
        List<DayWaterConsumption> dayList = getRecent();
        Map<LocalDate, DayWaterConsumption> map = dayList.stream().collect(Collectors.toMap(DayWaterConsumption::getRecordTime, a -> a));
        dateList.forEach(date -> {
            if (map.get(date) == null) {
                map.put(date, createByDate(date));
            }
        });
        return map.values().stream().sorted(Comparator.comparing(DayWaterConsumption::getRecordTime)).collect(Collectors.toList());
    }
    private DayWaterConsumption createByDate(LocalDate date) {
        DayWaterConsumption dayWaterConsumption = new DayWaterConsumption();
        dayWaterConsumption.setTotal(BigDecimal.ZERO);
        dayWaterConsumption.setPuck(BigDecimal.ZERO);
        dayWaterConsumption.setSlip(BigDecimal.ZERO);
        dayWaterConsumption.setCurling(BigDecimal.ZERO);
        dayWaterConsumption.setRecordTime(date);
        return dayWaterConsumption;
    }
}
