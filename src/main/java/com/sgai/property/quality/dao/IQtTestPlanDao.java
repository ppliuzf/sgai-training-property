package com.sgai.property.quality.dao;
import com.sgai.property.quality.entity.QtPlanItem;
import com.sgai.property.quality.entity.QtTestPlan;
import com.sgai.property.quality.vo.PlanVo;
import com.sgai.property.quality.vo.UnLinkItemVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import com.sgai.property.commonService.dao.MoreDataSourceDao;
@Mapper
public interface IQtTestPlanDao extends MoreDataSourceDao<QtTestPlan>{
    List findListByMap(PlanVo planVo);

    /**
     * 获得未关联的检查项列表
     * @return
     */
    List getUnLinkList(UnLinkItemVo unLinkItemVo);
    /**
     * 查询关联的检查项原始id
     * @param relQuery
     * @return
     */
    List<String> findRelIds(QtPlanItem qtPlanItem);

    /**
     * 根据任务专业
     * @param entity
     * @return
     */
    List<QtTestPlan> findListByTId(QtTestPlan entity);
}