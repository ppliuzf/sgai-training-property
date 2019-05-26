package com.sgai.property.park.service.impl;

import com.sgai.property.park.dto.IntervalRecord;
import com.sgai.property.park.entity.InOutRecord;
import com.sgai.property.park.mapper.InOutRecordMapper;
import com.sgai.property.park.service.InOutRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author ppliu
 * created in 2019/1/18 9:14
 */
@Service
public class InOutRecordServiceImpl implements InOutRecordService {
    @Autowired
    private InOutRecordMapper inOutRecordMapper;

    @Override
    public int insert(InOutRecord record) {
        return inOutRecordMapper.insert(record);
    }

    @Override
    public int insertSelective(InOutRecord record) {
        return inOutRecordMapper.insertSelective(record);
    }

    @Override
    public List<InOutRecord> selectByExample(Example example) {
        return inOutRecordMapper.selectByExample(example);
    }

    @Override
    public List<IntervalRecord> getIntervalRecordToday() {
        List<InOutRecord> inOutRecordList = getDataToday();
        Map<String, List<InOutRecord>> map = inOutRecordList.stream()
                .collect(Collectors.groupingBy(inOutRecord -> {
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(inOutRecord.getPassTime());
                    return calendar.get(Calendar.HOUR_OF_DAY) + "";
                }));
        List<IntervalRecord> intervalRecordList = new ArrayList<>();
        map.forEach((k, v) -> {
            IntervalRecord intervalRecord = new IntervalRecord();
            intervalRecord.setFlow(new BigDecimal(v.size()));
            intervalRecord.setInterVal(k + ":00-" + (Integer.valueOf(k) + 1) + ":00");
            intervalRecord.setTimeSorter(Integer.valueOf(k));
            intervalRecordList.add(intervalRecord);
        });
        return intervalRecordList;
    }

    @Override
    public IntervalRecord getFlowToday() {
        IntervalRecord intervalRecord = new IntervalRecord();
        intervalRecord.setInterVal("total");
        List<InOutRecord> inOutRecordList = getDataToday();
        intervalRecord.setFlow(new BigDecimal(inOutRecordList.size()));
        intervalRecord.setFlowIn(new BigDecimal(inOutRecordList.stream().filter(inOutRecord -> inOutRecord.getInOrOut().equals("1")).count()));
        intervalRecord.setFlowOut(new BigDecimal(inOutRecordList.stream().filter(inOutRecord -> inOutRecord.getInOrOut().equals("2")).count()));
        return intervalRecord;
    }

    private List<InOutRecord> getDataToday() {
        Example example = new Example(InOutRecord.class);
        example.createCriteria().andCondition("to_days(pass_time) = to_days(now())");
        return selectByExample(example);
    }

}
