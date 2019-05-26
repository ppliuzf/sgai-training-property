package com.sgai.property.supplier.dao;

import com.sgai.property.supplier.entity.HtContract;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface IHtContractDao {
    /**
     * 分页查询
     * @param var
     * @return
     */
    List<HtContract> findPageList(HtContract var);

    /**
     * 统计
     * @param var
     * @return
     */
    int getCount(HtContract var);

    /**
     * 查询对象
     * @param id
     * @return
     */
    HtContract getById(String id);
 
}