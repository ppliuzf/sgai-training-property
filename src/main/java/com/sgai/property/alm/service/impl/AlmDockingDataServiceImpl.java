package com.sgai.property.alm.service.impl;

import com.sgai.property.alm.mapper.AlmDockingDataMapper;
import com.sgai.property.alm.entity.AlmDockingData;
import com.sgai.property.alm.service.AlmDockingDataService;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @author ppliu
 * created in 2019/1/11 10:44
 */
@Service
public class AlmDockingDataServiceImpl implements AlmDockingDataService {
    @Autowired
    private AlmDockingDataMapper almDockingDataMapper;

    @Override
    public void save(AlmDockingData almDockingData) {
        almDockingDataMapper.insert(almDockingData);
    }

    @Override
    public List<AlmDockingData> selectAll() {
        return almDockingDataMapper.selectAll();
    }

    @Override
    public List<AlmDockingData> selectByExample(Example example) {
        return almDockingDataMapper.selectByExample(example);
    }

    @Override
    public List<AlmDockingData> getRecentList(int pageSize) {
        RowBounds rowBounds = new RowBounds(1, pageSize);
        Example example = new Example(AlmDockingData.class);
        example.orderBy("timestamp").desc();
        return almDockingDataMapper.selectByExampleAndRowBounds(example,rowBounds);
    }
}
