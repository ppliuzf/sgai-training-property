package com.sgai.property.alm.service;

import com.sgai.property.alm.entity.AlmDockingData;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @author ppliu
 * created in 2019/1/11 10:43
 */
public interface AlmDockingDataService {
    void save(AlmDockingData almDockingData);

    List<AlmDockingData> selectAll();

    List<AlmDockingData> selectByExample(Example example);

    List<AlmDockingData> getRecentList(int pageSize);

}
