package com.sgai.property.contract.dao;

import com.sgai.property.commonService.dao.MoreDataSourceDao;
import com.sgai.property.contract.entity.HtTemplet;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created by gaojianqun on 2018/2/23.
 */

@Mapper
public interface IHtTempletDaoMapper extends MoreDataSourceDao<HtTemplet> {

    //根据名称获取合同模板
    HtTemplet getHtTempletByName(HtTemplet htTemplet);

    //根据唯一标识获取合同模板
    HtTemplet getHtTempletById(HtTemplet htTemplet);

    //获取模板信息列表
    List<HtTemplet> findHtTempletList(HtTemplet htTemplet);

    //根据条件获取合同模板数量
    int getListCount(HtTemplet htTemplet);

}
