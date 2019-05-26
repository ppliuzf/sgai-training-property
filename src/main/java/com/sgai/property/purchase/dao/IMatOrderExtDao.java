package com.sgai.property.purchase.dao;

import com.sgai.property.commonService.dao.MoreDataSourceDao;
import com.sgai.property.depot.entity.MatOrder;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created by 145811 on 2018/1/11.
 */
@Mapper
public interface IMatOrderExtDao extends MoreDataSourceDao<MatOrder> {
    List<MatOrder> findApproveList(MatOrder order);
}
