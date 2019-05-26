package com.sgai.property.quality.dao;
import com.sgai.property.quality.entity.QtDefectOrder;
import com.sgai.property.quality.vo.DefectOrderVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;
import com.sgai.property.commonService.dao.MoreDataSourceDao;
@Mapper
public interface IQtDefectOrderDao extends MoreDataSourceDao<QtDefectOrder>{
    /**
     * 根据工单ID和工单原状态，来更新工单状态
     * @return
     */
    int updateByIdAndStatus(Map<String, Object> map);

    List<DefectOrderVo> getOrdersByStatus(DefectOrderVo defectOrderVo);
    /**
     * 单独处理改派，只有工单处于处理中，且当前操作人是发起人或处理人时，才可以撤消，避免多人改派的问题
     * @param map
     * @return
     */
    int redistributeByStatusAndUser(Map<String, Object> map);
}