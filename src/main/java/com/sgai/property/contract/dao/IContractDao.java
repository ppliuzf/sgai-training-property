package com.sgai.property.contract.dao;

import com.sgai.property.contract.entity.HtContract;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface IContractDao {

    /**
     * 统计未删除
     * @return
     */
    int getCount(HtContract htContract);

    /**
     * 查询最新一条数据的编码
     * @return
     */
    String getNumber();
    /**
     * 搜索
     * @param htContract
     * @return
     */
    List<HtContract> findList(HtContract htContract);

    /**
     * 获取实体详情
     * @param htContract
     * @return
     */
    HtContract get(HtContract htContract);
}