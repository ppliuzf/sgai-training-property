package com.sgai.property.purchase.dao;
import com.sgai.property.commonService.dao.MoreDataSourceDao;
import com.sgai.property.purchase.entity.Plan;
import org.apache.ibatis.annotations.Mapper;
@Mapper
public interface IPlanDao extends MoreDataSourceDao<Plan> {
 
}