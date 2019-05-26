package com.sgai.property.contract.dao;

import com.sgai.property.contract.entity.HtType;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ConITypeDao {

    /**
     * 分页查询
     * @param type
     * @return
     */
    List<HtType> findList(HtType type);

    /**
     * 统计
     * @return
     */
    int getCount(HtType type);

    HtType getByName(HtType type);
 
}