package com.sgai.property.customer.dao;
import com.sgai.property.commonService.dao.MoreDataSourceDao;
import com.sgai.property.customer.entity.WorkorderCost;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface IWorkorderCostDao extends MoreDataSourceDao<WorkorderCost> {
 
}