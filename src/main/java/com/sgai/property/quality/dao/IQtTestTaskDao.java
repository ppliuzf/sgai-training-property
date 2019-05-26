package com.sgai.property.quality.dao;
import com.sgai.property.commonService.dao.MoreDataSourceDao;
import com.sgai.property.quality.entity.QtTestTask;
import com.sgai.property.quality.vo.TaskResultVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface IQtTestTaskDao extends MoreDataSourceDao<QtTestTask>{
    int updateByStatus(Map<String, Object> map);

    List<TaskResultVo> listTaskResult(TaskResultVo taskResultVo);

    /**
     * 根据任务的开始时间与当前时间比对，把未开始的任务变为进行中
     * @param currentTimestamp
     * @return
     */
    int updateStatusByTime(Long currentTimestamp);
 
}