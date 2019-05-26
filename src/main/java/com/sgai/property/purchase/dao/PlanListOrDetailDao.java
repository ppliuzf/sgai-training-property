package com.sgai.property.purchase.dao;

import com.sgai.property.commonService.dao.MoreDataSourceDao;
import com.sgai.property.purchase.entity.Plan;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created by Administrator on 2018/2/27.
 */
@Mapper
public interface PlanListOrDetailDao extends MoreDataSourceDao<Plan> {
    List<Plan> getPlanList(Plan plan);

    List<Plan> getPlanApproveList(Plan plan);
}
