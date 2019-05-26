package com.sgai.property.quality.dao;
import com.sgai.property.commonService.dao.MoreDataSourceDao;
import com.sgai.property.quality.entity.QtPlanItemGroup;
import com.sgai.property.quality.vo.OptionVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface IQtPlanItemGroupDao extends MoreDataSourceDao<QtPlanItemGroup>{
    /**
     * 查询方案中组的最大排序序号
     * @param sortQuery
     * @return
     */
    Integer getMaxSort(QtPlanItemGroup sortQuery);
    /**
     * 其他组下拉列表
     * @param query
     * @return
     */
    List<OptionVo> findOtherGroupList(QtPlanItemGroup query);
}