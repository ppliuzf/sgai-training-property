package com.sgai.property.reformer.service.impl;

import com.sgai.property.reformer.entity.HourWaterConsumption;
import com.sgai.property.reformer.service.HourWaterConsumptionService;
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
 * created in 2019/1/23 15:03
 */
@Service
public class HourWaterConsumptionServiceImpl extends AbstractMapperService<HourWaterConsumption> implements HourWaterConsumptionService {
    private List<LocalDateTime> timeList = DateUtil.getRecentTime(24);

    public List<HourWaterConsumption> getRecent() {
        Example example = new Example(HourWaterConsumption.class);
        example.createCriteria().andIn("recordTime", timeList);
        return selectByExample(example);
    }

    @Override
    public List<HourWaterConsumption> getLast7HoursData() {
        List<HourWaterConsumption> hourList = getRecent();
        Map<LocalDateTime, HourWaterConsumption> map = hourList.stream()
                .collect(Collectors.toMap(HourWaterConsumption::getRecordTime, a -> a, (k1, k2) -> k1));
        timeList.forEach(time -> {
            if (map.get(time) == null) {
                map.put(time, createByTime(time));
            }
        });
        return map.values()
                .stream()
                .filter(hourData -> hourData.getRecordTime().getHour() % 3 == 0)
                .sorted(Comparator.comparing(HourWaterConsumption::getRecordTime))
                .collect(Collectors.toList());
    }

    private HourWaterConsumption createByTime(LocalDateTime time) {
        HourWaterConsumption hourWaterConsumption = new HourWaterConsumption();
        hourWaterConsumption.setTotal(BigDecimal.ZERO);
        hourWaterConsumption.setPuck(BigDecimal.ZERO);
        hourWaterConsumption.setCurling(BigDecimal.ZERO);
        hourWaterConsumption.setSlip(BigDecimal.ZERO);
        hourWaterConsumption.setRecordTime(time);
        return hourWaterConsumption;
    }
}
