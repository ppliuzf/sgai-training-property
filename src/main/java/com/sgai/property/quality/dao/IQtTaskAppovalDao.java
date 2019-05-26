package com.sgai.property.quality.dao;
import com.sgai.property.quality.entity.QtTaskAppoval;
import com.sgai.property.commonService.dao.MoreDataSourceDao;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface IQtTaskAppovalDao extends MoreDataSourceDao<QtTaskAppoval>{
    /**
     * 查询意见审批通过的
     * @param appovalQuery
     * @return
     */
    List<QtTaskAppoval> findListChecked(QtTaskAppoval appovalQuery);

    /**
     * 批量插入审批明细
     * @param qtTaskAppovals
     * @return
     */
    boolean batchInsertTaskApproval(List<QtTaskAppoval> qtTaskAppovals);

    boolean updateApprovalStatus(QtTaskAppoval appovalQuery);
    /**
     * 找到自己的审核状态
     * @param appovalQuery
     * @return
     */
    List<QtTaskAppoval> findStatusBySelf(QtTaskAppoval appovalQuery);
}