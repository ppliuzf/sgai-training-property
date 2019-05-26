package com.sgai.property.quality.dao.plan;
import com.sgai.property.commonService.dao.MoreDataSourceDao;
import com.sgai.property.quality.entity.plan.Task;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface ITaskDao extends MoreDataSourceDao<Task> {

    /**
     * 根据计划查询
     * @param entity
     * @return
     */
    List<Task> findListByRecordId(Task entity);

    /**
     * 根据模板id查询
     * @param entity
     * @return
     */
    List<Task> findListByTemplateId(Task entity);

    Task getByRecordIdAndTemplateId(Task entity);

    boolean updateByIdAndTpId(Map<String,Object> map);
}