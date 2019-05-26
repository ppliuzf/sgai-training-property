package com.sgai.property.park.service;


import com.sgai.property.park.dto.IntervalRecord;
import com.sgai.property.park.entity.InOutRecord;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @author ppliu
 * created in 2018/11/15 15:16
 */
public interface InOutRecordService {
    int insert(InOutRecord record);

    int insertSelective(InOutRecord record);

    List<InOutRecord> selectByExample(Example example);

    /**
     * 获取当日时段流量数据.
     */
    List<IntervalRecord> getIntervalRecordToday();

    /**
     * 当日总体流量数据.
     */
    IntervalRecord getFlowToday();

}
